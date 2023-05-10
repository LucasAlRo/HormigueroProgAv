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
public class HormigaObrera extends Hormiga {
    
    public HormigaObrera(int idHormiga, Colonia c) {
        super(idHormiga, c);
        setTipo("Obrera");
        this.setNombre("HO"+ formatoId(idHormiga));
    }
    
    @Override
    public void run(){
        
        this.getC().consolaLog("La hormiga" + getNombre() + " ha nacido");
        this.getC().entrarColonia(this);
        
        while(true){
            try{
                for(int i = 0; i<=10; i++){
                    if ((getIdhormiga()%2)!=0){
                        this.getC().salirColonia(this);
                        Thread.sleep(4000);
                        this.getC().entrarColonia(this);
                        this.getC().reponerAlmacen(this); 
                    }
                    else{
                        this.getC().reponerComedor(this);
                    }
                }
                
                this.getC().comer(Util.intAleat(1000, 3000),this);
                this.getC().descansar(1000,this);

            }catch(InterruptedException e){}  
        
    } 
}
}
