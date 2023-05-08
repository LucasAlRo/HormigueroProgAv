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
    private ListaThreads exterior;
    private ListaThreads instruccion;
    private ListaThreads descanso;
    private ListaThreads almacen;
    private ListaThreads llevandoComida;
    private ListaThreads comedor;

    // Pausa  con monitor
    boolean Pausa = false;

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
            JTextField jinstruccion, JTextField jdescanso,
            JTextField jalmacen, JTextField jcontAlmacen, JTextField jllevando,
            JTextField jcomedor, JTextField jcontComedor) {

        exterior = new ListaThreads(jexterior);
        colonia = new ListaThreads(jcolonia);
        instruccion = new ListaThreads(jinstruccion);
        descanso = new ListaThreads(jdescanso);
        almacen = new ListaThreads(jalmacen);
        llevandoComida = new ListaThreads(jllevando);
        comedor = new ListaThreads(jcomedor);
        
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
            semEntrada.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
            consolaLog("La hormiga"  + h.getNombre()  + "se  acerca al almacen para depositar comida");
            aforoAlmacen.acquire();
            comprobarPausa();
            consolaLog("La hormiga"  + h.getNombre()  + "accede al almacen  para depositar comida");
            almacen.meter(h);
            semComidaAlm.acquire();
            Thread.sleep(Util.intAleat(2000, 4000));
            consolaLog("La hormiga"  + h.getNombre()  + "deposita comida en el almacen");
            comidaAlmacen += 5;
            jcontAlmacen.setText(Integer.toString(comidaAlmacen));
            semComidaAlm.release();
            semAlmVacio.release();
            comprobarPausa();
            consolaLog("La hormiga"  + h.getNombre()  + "abandona el almacen");
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
            consolaLog("La hormiga"  + h.getNombre()  + "se  acerca al almacen para recoger comida");
            aforoAlmacen.acquire();
            comprobarPausa();
            consolaLog("La hormiga"  + h.getNombre()  + "accede al almacen para recoger comida");
            almacen.meter(h);
            semAlmVacio.acquire();
            semComidaAlm.acquire();
            Thread.sleep(Util.intAleat(1000, 2000));
            comprobarPausa();
            consolaLog("La hormiga"  + h.getNombre()  + "recoge comida del almacen");
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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

}
