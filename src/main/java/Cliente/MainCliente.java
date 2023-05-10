/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Cliente;

import CompartidosRMI.MetodosCompartidos;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author lucas
 */
public class MainCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
        // TODO code application logic here
        Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099);
        MetodosCompartidos metodos = (MetodosCompartidos) registro.lookup("//127.0.0.1/Colonia");
        InterfazCliente iCliente = new InterfazCliente(metodos);
        iCliente.setVisible(true);
        
        while (true){
            iCliente.actualizar();
        }
    }
    
}
