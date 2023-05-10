/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import static Server.Util.intAleat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class Generador extends Thread {

    Colonia c;
    ListaThreads exterior;

    public Generador(Colonia c) {
        this.c = c;
        this.exterior = this.c.getExterior();
    }

    @Override
    public void run() {
        int SoldCria = 1;

        // Bucle for donde "k" es el numero de hormigas a generar (10.000 en el enunciado)
        for (int k = 1; k <= 10000; k++) {
            try {
                c.comprobarPausa();
                // Se espera un tiempo aleatorio entre 0,8 y 3,5 segundos
                Thread.sleep(intAleat(800, 3500));
                c.comprobarPausa();
            } catch (InterruptedException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Generacion de la obrera:
            HormigaObrera oo = new HormigaObrera(k, c);
            try {
                exterior.meter(oo);
                c.getObrerasTotales().meter(oo);
                c.getHormigasTotales().add(oo);
                oo.start();
            } catch (InterruptedException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            // Cada tres hormigas obreras, se generan una soldado y una cria
            if (k % 3 == 0 && k != 0) {
                HormigaSoldado os = new HormigaSoldado(SoldCria, c);
                HormigaCria oc = new HormigaCria(SoldCria, c);
                SoldCria++;
                try {
                    Thread.sleep(intAleat(800, 3500));
                    exterior.meter(oc);
                    c.getCriasTotales().meter(oc);
                    c.getHormigasTotales().add(oc);
                    oc.start();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Thread.sleep(intAleat(800, 3500));
                    exterior.meter(os);
                    c.getSoldadosTotales().meter(os);
                    c.getHormigasTotales().add(os);
                    os.start();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }
}
