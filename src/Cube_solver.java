import java.util.*;

public class Cube_solver extends Thread
{
    private final int MAX_MOVES = 20;
    private static Set<Cube_3x3x3> visited;
    private PriorityQueue<Cube_3x3x3> st_buffer;
    private Cube_3x3x3 aux;

    public Cube_solver(Cube_3x3x3 initial)
    {
        visited = new HashSet<>();
        st_buffer = new PriorityQueue<>();
        st_buffer.add(initial);
        aux = initial;
    }

    public Cube_3x3x3 solve() throws InterruptedException
    {
        boolean solved = false;
        int cont = 0;
        while (!solved && !st_buffer.isEmpty())
        {
           // System.out.println(st_buffer);
            aux=st_buffer.poll(); //sacamos el primero de la cola
            solved = aux.solved();
            //System.out.println("\n\nExpanded\n"+aux);

            for (Cube_3x3x3 desc : expand_node(aux)) //recorremos los hijos del nodo
            {
                //System.out.println(desc+"\npunt: "+desc.puntuation());
               // System.out.println("\n\n\n\n"+Cube_solver.visited+"\n\n\n\n");
                //se añaden si no han sido visitados

                    //System.out.println("\n\n\n\n\nADD SON\n"+desc+"\npunt: "+desc.puntuation()+"\n\n\n\n\n");
                    this.st_buffer.add(desc);


            }
            Cube_solver.visited.add(aux);
            System.out.println("-------------------\n\n\n");

        }


        if (aux.solved())
            System.out.println("Cube solved: \n"+aux);
        return aux;
    }

    public List<Cube_3x3x3> expand_node(Cube_3x3x3 node)
    {
        List<Cube_3x3x3> hij = new LinkedList<>();

        for (int i=0;i<6;i++)
        {
            Cube_3x3x3 son = new Cube_3x3x3(node); //se crea igual que el padre
            son.move(i); //hace un movimiento
            hij.add(son);
        }

        return hij;
    }
}
