/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import static Server.Util.formatoId;

/**
 *
 * @author lucas
 */
public class HormigaSoldado extends Hormiga{      
    
    // Constructor
    public HormigaSoldado(int idHormiga, Colonia c) {
        super(idHormiga, c);
        setTipo("Soldado");
        this.setNombre("HS"+ formatoId(idHormiga));
    }
    
    @Override
    public void run() {
        
        this.getC().consolaLog("La hormiga" + getNombre() + " ha nacido");
        this.getC().entrarColonia(this);
        
        while (true) {

            for (int k = 0; k <= 10; k++) {
                this.getC().comenzarEntrenamiento(this);
                this.getC().terminarEntrenamiento(this);
                this.getC().descansar(2000, this);
                System.out.println("Hormiga soldado esta ");
            }
            //this.getC().comer(3000, this);

        }
    }
    
}
