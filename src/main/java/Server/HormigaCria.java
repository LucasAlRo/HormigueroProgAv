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
public class HormigaCria extends Hormiga {
    
    public HormigaCria(int idHormiga, Colonia c) {
        super(idHormiga, c);
        setTipo("Cria");
        this.setNombre("HC"+ formatoId(idHormiga));
    }
    
    @Override
    public void run(){
        
        this.getC().consolaLog("La hormiga" + getNombre() + " ha nacido");
        this.getC().entrarColonia(this);
        
        // En estado de amenaza, las crias que entren a la colonia deberan 
        // refugiarse tambien
        if (this.getC().comprobarAmenaza()) {
            this.interrupt();
        }
        
        while(true){
            
                this.getC().comer(Util.intAleat(3000,5000), this);
                this.getC().descansar(4000, this);   
        
        }
    } 
}
