/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class Colonia {

    // Ubicaciones de la colonia:
    private ListaThreads colonia;
    private ListaThreads obrerasTotales;
    private ListaThreads buscando;
    private ListaThreads soldadosTotales;
    private ListaThreads criasTotales;
    private ListaThreads exterior;
    private ListaThreads instruccion;
    private ListaThreads descanso;
    private ListaThreads almacen;
    private ListaThreads llevandoComida;
    private ListaThreads comedor;
    private ListaThreads defendiendo;
    private ListaThreads refugio;

    // Lista de hormigas totales (utilizado en la invasion)
    private List<Hormiga> hormigasTotales;

    // Lista de hormigas crias comiendo (para RMI)
    private List<Hormiga> criasComiendo;
    private List<Hormiga> obrerasInterior;

    // Pausa  con monitor
    boolean Pausa = false;

    // Amenaza con monitor  y cyclic barrier
    boolean Amenaza = false;
    private CyclicBarrier formacionDefensiva;

    // LOG
    private FileWriter logWriter;
    private final PrintWriter pw;
    private final DateTimeFormatter formatoFecha;

    // Contadores de comida
    private int comidaAlmacen;
    private int comidaComedor;

    private JTextField jcontAlmacen;
    private JTextField jcontComedor;

    // Semaforos:
    // Entrada:
    private Semaphore semEntrada = new Semaphore(1);  // Un solo tunel
    // Salida:
    private Semaphore semSalida = new Semaphore(2);  //  Dos tuneles 

    // Semaforos almacen:
    private Semaphore aforoAlmacen = new Semaphore(10);
    private Semaphore semComidaAlm = new Semaphore(1);
    private Semaphore semAlmVacio = new Semaphore(0);

    // Semaforos comida:
    private Semaphore semComidaCom = new Semaphore(1);
    private Semaphore semComVacio = new Semaphore(0);

    // CONSTRUCTOR
    public Colonia(JTextField jexterior, JTextField jcolonia,
            JTextField jobreras, JTextField jsoldados, JTextField jcrias,
            JTextField jinstruccion, JTextField jdescanso,
            JTextField jalmacen, JTextField jcontAlmacen, JTextField jllevando,
            JTextField jcomedor, JTextField jcontComedor, JTextField jdefendiendo,
            JTextField jrefugio, JTextField jbuscando) {

        this.exterior = new ListaThreads(jexterior);
        this.colonia = new ListaThreads(jcolonia);
        this.obrerasTotales = new ListaThreads(jobreras);
        this.soldadosTotales = new ListaThreads(jsoldados);
        this.criasTotales = new ListaThreads(jcrias);
        this.instruccion = new ListaThreads(jinstruccion);
        this.descanso = new ListaThreads(jdescanso);
        this.almacen = new ListaThreads(jalmacen);
        this.llevandoComida = new ListaThreads(jllevando);
        this.comedor = new ListaThreads(jcomedor);
        this.defendiendo = new ListaThreads(jdefendiendo);
        this.refugio = new ListaThreads(jrefugio);
        this.buscando = new ListaThreads(jbuscando);

        this.hormigasTotales = new ArrayList<>();
        this.criasComiendo = new ArrayList<>();
        this.obrerasInterior = new ArrayList<>();

        this.jcontAlmacen = jcontAlmacen;
        this.jcontComedor = jcontComedor;

        //  LOG
        try {
            this.logWriter = new FileWriter("LogHormiguero.txt");
        } catch (IOException ex) {
        }
        this.pw = new PrintWriter(logWriter);
        this.formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }

    // Metodo para que las hormigas entren en la colonia:
    public void entrarColonia(Hormiga h) {
        try {
            comprobarPausa();
            semEntrada.acquire();
            consolaLog("La hormiga" + h.getNombre() + " se mete en en tunel de entrada");
            Thread.sleep(100); //  Tardan 0,1 segundos
            colonia.meter(h);
            exterior.sacar(h);
            consolaLog("La hormiga" + h.getNombre() + " ha entrado en la colonia");
            if (h.getTipo().equals("Obrera")) {
                obrerasInterior.add(h);
            }
            semEntrada.release();
        } catch (InterruptedException ex) {
            refugiarse(h); //Solamente las hormigas crias son interrumpidas al entrar
        }
    }

    // Metodo para que  las hormigas salgan de la colonia:
    // (al no finalizar el programa, solo se usa para buscar comida y para defender
    // la colonia en caso de ataque)
    public void salirColonia(Hormiga h) {
        try {
            comprobarPausa();
            semSalida.acquire();
            Thread.sleep(100);
            consolaLog("La hormiga" + h.getNombre() + " sale de la colonia");
            colonia.sacar(h);
            exterior.meter(h);
            if (h.getTipo().equals("Obrera")) {
                buscando.meter(h);
                obrerasInterior.remove(h);
            }
            semSalida.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo para que las hormigas solado entrenen:
    public void comenzarEntrenamiento(Hormiga h) {
        try {
            comprobarPausa();
            instruccion.meter(h);
            consolaLog("La hormiga" + h.getNombre() + " comienza a entrenar");
            Thread.sleep(Util.intAleat(2000, 8000)); // De 2 a 8 segundos
        } catch (InterruptedException e) {
            consolaLog("La hormiga" + h.getNombre() + " deja de entrenar y va a defender la colonia");
            defenderColonia(h);
        }
    }

    // Metodo para que las hormigas soldado dejen de entrenar:
    public void terminarEntrenamiento(Hormiga h) {
        try {
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + " ha terminado de entrenar");
            instruccion.sacar(h);
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo para ir a la zona de descanso: 
    public void descansar(int tiempo, Hormiga h) {
        try {
            comprobarPausa();
            descanso.meter(h);
            consolaLog("La hormiga" + h.getNombre() + " se va a la sala de descanso");
            Thread.sleep(tiempo);
            comprobarPausa();
        } catch (InterruptedException e) {
            if (h.getTipo().equals("Soldado")) {
                consolaLog("La hormiga" + h.getNombre() + " deja de descansar y va a defender la colonia");
                defenderColonia(h);
            } else if (h.getTipo().equals("Cria")){
                try {
                    consolaLog("La cria" + h.getNombre() + " deja de descansar y se va al refugio");
                    descanso.sacar(h);
                    refugiarse(h);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        dejarDescanso(h);
    }

    // Metodo para abandonar la zona de descanso:
    public void dejarDescanso(Hormiga h) {
        try {
            comprobarPausa();
            descanso.sacar(h);
            consolaLog("La hormiga" + h.getNombre() + " ha terminado de descansar y abandona la sala");
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo para acceder al almaceny depositar comida:
    public void reponerAlmacen(Hormiga h) {
        try {
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + "se  acerca al almacen para depositar comida");
            aforoAlmacen.acquire();
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + "accede al almacen  para depositar comida");
            almacen.meter(h);
            semComidaAlm.acquire();
            Thread.sleep(Util.intAleat(2000, 4000));
            consolaLog("La hormiga" + h.getNombre() + "deposita comida en el almacen");
            comidaAlmacen += 5;
            jcontAlmacen.setText(Integer.toString(comidaAlmacen));
            semComidaAlm.release();
            semAlmVacio.release();
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + "abandona el almacen");
            almacen.sacar(h);
            aforoAlmacen.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo para recoger comida del almacen y llevarlo a la zona de comer:
    public void reponerComedor(Hormiga h) {
        try {
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + "se  acerca al almacen para recoger comida");
            aforoAlmacen.acquire();
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + "accede al almacen para recoger comida");
            almacen.meter(h);
            semAlmVacio.acquire();
            semComidaAlm.acquire();
            Thread.sleep(Util.intAleat(1000, 2000));
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + "recoge comida del almacen");
            comidaAlmacen -= 5;
            jcontAlmacen.setText(Integer.toString(comidaAlmacen));
            semComidaAlm.release();

            // Termina de recoger la comida y se marcha
            aforoAlmacen.release();
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + " se lleva comida del almacen");
            almacen.sacar(h);
            llevandoComida.meter(h);
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + " lleva comida al comedor");
            Thread.sleep(Util.intAleat(1000, 3000));

            // Llega al comedor con la comida
            comprobarPausa();
            llevandoComida.sacar(h);
            comedor.meter(h);
            consolaLog("La hormiga" + h.getNombre() + " llega al comedor con comida");

            // Deposita la comida en el comedor
            Thread.sleep(Util.intAleat(1000, 2000));
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + " deja la comida en el comedor");
            comidaComedor += 5;
            jcontComedor.setText(Integer.toString(comidaComedor));

            // Por ultimo, se marcha
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + " abandona el comedor");
            comedor.sacar(h);
            semComidaCom.release();
            semComVacio.release();
            aforoAlmacen.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo para que las hormigas coman:
    public void comer(int tiempo, Hormiga h) {

        try {
            comprobarPausa();
            comedor.meter(h);
            consolaLog("La hormiga" + h.getNombre() + " tiene hambre y se mete al comedor");
            semComVacio.acquire();
            comprobarPausa();
            consolaLog("La hormiga" + h.getNombre() + " comienza a comer");
            semComidaCom.acquire();
            comidaComedor--;
            jcontComedor.setText(Integer.toString(comidaComedor));
            semComidaCom.release();
            Thread.sleep(tiempo);   // Diferentes tiempos segun la hormiga
            comprobarPausa();
            System.out.println("La hormiga" + h.getNombre() + " ha terminado de comer y se va");
            comedor.sacar(h);
        } catch (InterruptedException ex) {
            if (h.getTipo().equals("Soldado")) {
                consolaLog("La hormiga" + h.getNombre() + " deja de comer y va a defender la colonia");
                defenderColonia(h);
            } else {
                try {
                    consolaLog("La cria" + h.getNombre() + " deja de comer y va a refugiarse");
                    comedor.sacar(h);
                    refugiarse(h);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }

    }

    // Metodo para escribir en el log:
    public void escribirLog(String mensaje) {
        try {
            pw.println(formatoFecha.format(LocalDateTime.now()) + "-> " + mensaje);
            logWriter.flush();
        } catch (IOException e) {
        }
    }

    // Metodo para escribir en consola y el log en una  llamada:
    public void consolaLog(String mensaje) {
        System.out.println(mensaje);
        escribirLog(mensaje);
    }

    public ListaThreads getExterior() {
        return exterior;
    }

    // Metodos para la pausa:
    public synchronized void pausar() {
        Pausa = true;
    }

    public synchronized void reanudar() {
        Pausa = false;
        notifyAll(); // Se hace un notify a todos los hilos que esperan
    }

    // Los hilos comprueban si se ha pausado el programa
    // De ser asi, esperan hasta que se vuelva a reanudar
    public synchronized void comprobarPausa() throws InterruptedException {
        while (Pausa) {
            wait();
        }
    }

    // Metodos de la invasion:
    // Utilizada por claridad en el codigo
    public synchronized boolean comprobarAmenaza() {
        return this.Amenaza;
    }

    //  Al ser un estado de amenaza, se puede utilizar un booleano que represente 
    //  si la colonia esta siendo atacada (no se genera un objeto como tal)
    public synchronized void generarInsectoInvasor() {
        try {
            comprobarPausa();
            this.Amenaza = true;
            consolaLog("¡Un insecto está atacando la colonia!");
            formacionDefensiva = new CyclicBarrier(soldadosTotales.getTamano());
            alertaAmenaza(hormigasTotales);
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Se interrumpe  la ejecucion de todos los hilos hormiga soldado y cria
    // (ya que las obreras siguen actuando igual)
    public synchronized void alertaAmenaza(List<Hormiga> hormiga) {
        for (Hormiga h : hormiga) {
            if (h.getTipo().equals("Soldado") || h.getTipo().equals("Cria")) {
                h.interrupt();
            }
        }
    }

    public void defenderColonia(Hormiga h) {
        try {
            comprobarPausa();
            instruccion.sacar(h);
            descanso.sacar(h);
            comedor.sacar(h);
            salirColonia(h);
            defendiendo.meter(h);
            consolaLog("¡La hormiga" + h.getNombre() + " se encuentra en  el frente defensivo!");
            formacionDefensiva.await();
            comprobarPausa();
            consolaLog("¡La hormiga" + h.getNombre() + " lucha contra el insecto atacante!");
            Thread.sleep(20000);
            comprobarPausa();
            consolaLog("¡La hormiga" + h.getNombre() + " ha vencido al insecto!");
            finalizarInvasion();
            defendiendo.sacar(h);
            entrarColonia(h);
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void refugiarse(Hormiga h) {
        try {
            comprobarPausa();
            refugio.meter(h);
            consolaLog("¡La cria" + h.getNombre() + " se resguarda del ataque en el refugio!");
            esperarAmenaza(h);
            comprobarPausa();
            consolaLog("La cria" + h.getNombre() + " ya esta a salvo y sale del refugio");
            refugio.sacar(h);
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo para que las crias esperen a que cese la amenaza
    public synchronized void esperarAmenaza(Hormiga h) {
        while (this.Amenaza) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Se notifica a las hormigas que ha terminado el ataque
    public synchronized void finalizarInvasion() {
        this.Amenaza = false;
        notifyAll();
    }

    public ListaThreads getObrerasTotales() {
        return obrerasTotales;
    }

    public void setObrerasTotales(ListaThreads obrerasTotales) {
        this.obrerasTotales = obrerasTotales;
    }

    public ListaThreads getSoldadosTotales() {
        return soldadosTotales;
    }

    public void setSoldadosTotales(ListaThreads soldadosTotales) {
        this.soldadosTotales = soldadosTotales;
    }

    public ListaThreads getCriasTotales() {
        return criasTotales;
    }

    public void setCriasTotales(ListaThreads criasTotales) {
        this.criasTotales = criasTotales;
    }

    public List<Hormiga> getHormigasTotales() {
        return hormigasTotales;
    }

    public void setHormigasTotales(List<Hormiga> hormigasTotales) {
        this.hormigasTotales = hormigasTotales;
    }

    public Integer getNBuscando() {
        return buscando.getTamano();
    }

    public void setBuscando(ListaThreads buscando) {
        this.buscando = buscando;
    }

    public Integer getNObrerasInterior() {
        return obrerasInterior.size();
    }

    public Integer getNSoldadosInstruccion() {
        return instruccion.getTamano();
    }
    
    public Integer getNSoldadosDefendiendo(){
        return this.defendiendo.getTamano();
    }
    
    public Integer getNCriasComedor() {
        return this.criasComiendo.size();
    }
    
    public List<Hormiga> getCriasComedor(){
        return this.criasComiendo;
    }
    
    public Integer getNCriasRefugio(){
        return this.refugio.getTamano();
    }
    
    public Integer getNSoldadosTotales(){
        return this.soldadosTotales.getTamano();
    }

    public ListaThreads getBuscando() {
        return this.buscando;
    }
}
