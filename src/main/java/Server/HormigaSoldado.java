/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import static Server.Util.formatoId;
import java.util.concurrent.BrokenBarrierException;

/**
 *
 * @author lucas
 */
public class HormigaSoldado extends Hormiga {

    // Constructor
    public HormigaSoldado(int idHormiga, Colonia c) {
        super(idHormiga, c);
        setTipo("Soldado");
        this.setNombre("HS" + formatoId(idHormiga));
    }

    @Override
    public void run() {

        this.getC().consolaLog("La hormiga" + getNombre() + " ha nacido");
        this.getC().entrarColonia(this);
        int contador = 0;

        while (true) {

            if (contador != 6) {  // Cada 6 iteraciones ira a comer
                this.getC().comenzarEntrenamiento(this);
                this.getC().terminarEntrenamiento(this);
                this.getC().descansar(2000, this);
                contador++;
            } else {
                this.getC().comer(3000, this);
                contador = 0;
            }
        }
    }
}
