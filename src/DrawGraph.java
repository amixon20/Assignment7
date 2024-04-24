
import java.util.Scanner;

import org.graphstream.graph.EdgeRejectedException;
import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;


public class DrawGraph {

    public static void displayGraph(Graph graph) {
        Viewer viewer = graph.display();
        //View view = new View()
        viewer.enableAutoLayout();
        System.setProperty("org.graphstream.ui", "swing");

        for(Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }
        //viewer.addView(view);
    }


    public static void main(String[] args) {
        int labelIndex = 0;
        int xIndex = 0;
        Scanner scan  = new Scanner(System.in);
        System.out.println("Enter the size of the array (n): " + "\n");
        int n = Integer.parseInt(scan.nextLine());
        String[] labels = new String[n];
        int[] x = new int[n];
        System.out.println("Enter the array of the directed graph: " + "\n");

        String input = scan.nextLine();

            for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) != '[' && input.charAt(i) != ']' && input.charAt(i) != '('
                            && input.charAt(i) != ')' && input.charAt(i) != ' ' && input.charAt(i) != ','
                            && labelIndex < n && xIndex < 10) {
                        if (Character.isLetter(input.charAt(i))) {
                            labels[labelIndex] = Character.toString(input.charAt(i));
                            labelIndex++;
                        } else {
                            x[xIndex] = Character.getNumericValue(input.charAt(i));
                            xIndex++;
                        }
                    }
            }

        SingleGraph graph = new SingleGraph("Directed Graph");


        for(int i = 0; i < n; i++) {
            graph.addNode(labels[i]);
        }
        for (int i = 0; i < n; i++) {

            int positiveEdge = (i + x[i]) % n;
            int negativeEdge = ((i - x[i]) + n) % n;
            String edgeName1 = labels[i] + labels[positiveEdge];
            String edgeName2 = labels[i] + labels[negativeEdge];
            try {
                graph.addEdge(edgeName1, i, positiveEdge, true);
                graph.addEdge(edgeName2, i, negativeEdge, true);
            } catch (IndexOutOfBoundsException | EdgeRejectedException e) {
                throw new RuntimeException(e);
            } catch (IdAlreadyInUseException e) {
                return;
            }
        }
    displayGraph(graph);
    }
}