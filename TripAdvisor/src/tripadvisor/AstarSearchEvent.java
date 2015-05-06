/*
Reference: https://raymondrchua.wordpress.com/2013/12/21/a-star-search-algorithm-java-implementation/
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tripadvisor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class AstarSearchEvent {
    /*public static void main(String args[]) throws IOException{
        runAStar("Sixth_Floor_Museum_at_Dealey_Plaza","AT_T_Performing_Arts_Center");
    }
*/
    public static void runAStar(String source, String destination) throws FileNotFoundException, IOException {

        Map<String, Node> map = new HashMap<String, Node>();
        Node a = null, b = null, c = null, d = null, e = null, f = null, g = null, h = null, i = null, j = null, k = null, l = null;
        BufferedReader r = new BufferedReader(new FileReader("TripAdvisor_Data1.csv"));
        String line;
        while ((line = r.readLine()) != null) {
            if (line.split(",")[0].equalsIgnoreCase(destination)) {
                String[] vals = line.split(",");

                a = new Node("AT_T_Performing_Arts_Center", Double.parseDouble(vals[1].split(":")[0]));
                b = new Node("Buckner_Park", Double.parseDouble(vals[2].split(":")[0]));
                c = new Node("Dallas_Heritage_Village", Double.parseDouble(vals[3].split(":")[0]));
                d = new Node("Dallas_World_Aquarium", Double.parseDouble(vals[4].split(":")[0]));
                e = new Node("Dallas_Zoo", Double.parseDouble(vals[5].split(":")[0]));
                f = new Node("Klyde_Warren_Park", Double.parseDouble(vals[6].split(":")[0]));
                g = new Node("Meadows_Museum", Double.parseDouble(vals[7].split(":")[0]));
                h = new Node("Reunion_Tower", Double.parseDouble(vals[8].split(":")[0]));
                i = new Node("Rosewood_Mansion", Double.parseDouble(vals[9].split(":")[0]));
                j = new Node("Six_Flags", Double.parseDouble(vals[10].split(":")[0]));
                k = new Node("Sixth_Floor_Museum_at_Dealey_Plaza", Double.parseDouble(vals[11].split(":")[0]));
                l = new Node("Verizon_Theatre", Double.parseDouble(vals[12].split(":")[0]));
            }
        }

        //AT_T_Performing_Arts_Center
        a.adjacencies = new Edge[]{
            new Edge(b, 75),
            new Edge(d, 140),
            new Edge(f, 118),};

        //Buckner_Park
        b.adjacencies = new Edge[]{
            new Edge(a, 75),
            new Edge(c, 71),};

        //Dallas_Heritage_Village
        c.adjacencies = new Edge[]{
            new Edge(b, 71),
            new Edge(d, 151),};

        //Dallas_World_Aquarium
        d.adjacencies = new Edge[]{
            new Edge(a, 140),
            new Edge(c, 151),
            new Edge(e, 99),
            new Edge(g, 80),};

        //Dallas_Zoo
        e.adjacencies = new Edge[]{
            new Edge(d, 99),
            new Edge(k, 211),};

        //Klyde_Warren_Park
        f.adjacencies = new Edge[]{
            new Edge(a, 118),
            new Edge(h, 111)
        };

        //Meadows_Museum
        g.adjacencies = new Edge[]{
            new Edge(d, 80),
            new Edge(j, 97),
            new Edge(l, 146)
        };

        //Reunion_Tower
        h.adjacencies = new Edge[]{
            new Edge(f, 111),
            new Edge(i, 145)
        };
        //Rosewood_Mansion
        i.adjacencies = new Edge[]{
            new Edge(h, 145),
            new Edge(l, 120),};
        //Six_Flags
        j.adjacencies = new Edge[]{
            new Edge(g, 97),
            new Edge(k, 101),
            new Edge(l, 138),};
        //Sixth_Floor_Museum_at_Dealey_Plaza
        k.adjacencies = new Edge[]{
            new Edge(e, 211),
            new Edge(j, 101)
        };
        //Verizon_Theatre
        l.adjacencies = new Edge[]{
            new Edge(g, 146),
            new Edge(i, 120),
            new Edge(j, 138)
        };
//creating the graph
        map.put("AT_T_Performing_Arts_Center", a);
        map.put("Buckner_Park", b);
        map.put("Dallas_Heritage_Village", c);
        map.put("Dallas_World_Aquarium", d);
        map.put("Dallas_Zoo", e);
        map.put("Klyde_Warren_Park", f);
        map.put("Meadows_Museum", g);
        map.put("Reunion_Tower", h);
        map.put("Rosewood_Mansion", i);
        map.put("Six_Flags", j);
        map.put("Sixth_Floor_Museum_at_Dealey_Plaza", k);
        map.put("Verizon_Theatre", l);

//search from source to Destination 
        AStarSearchAlgo(map.get(source), map.get(destination));

//print path from source to destination
        //List<Node> path = printPath(map.get(destination));
        System.out.println(printPath(map.get(destination)));
    }

    public static String printPath(Node target) {
        List<Node> path = new ArrayList<Node>();
        double cost = 0;
        for (Node node = target; node != null; node = node.parent) {
            path.add(node);
            if(node.parent!=null){
            for(int i = 0; i < node.adjacencies.length; ++i){
                if(node.adjacencies[i].target == node.parent){
                    cost+=node.adjacencies[i].cost;
                }
            }
            }
        
        }
        Collections.reverse(path);
        return "Path Taken : "+path+"\n"+"Total Distance Travelled to reach Destination : "+cost+"miles.";
    }

    public static void AStarSearchAlgo(Node source, Node goal) {
        Set<Node> explored = new HashSet<Node>();
        PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new Comparator<Node>() {
            //override compare Method
            public int compare(Node i, Node j) {
                if (i.f_scores > j.f_scores) {
                    return 1;
                } else if (i.f_scores < j.f_scores) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        //cost from start
        source.g_scores = 0;
        queue.add(source);
        boolean found = false;
        while ((!queue.isEmpty()) && (!found)) {
            //the node in having the lowest f_score value
            Node current = queue.poll();
            explored.add(current);
            //goal found
            if (current.value.equals(goal.value)) {
                found = true;
            }
            //check every child of current node
            for (Edge e : current.adjacencies) {
                Node child = e.target;
                double cost = e.cost;
                double temp_g_scores = current.g_scores + cost;
                double temp_f_scores = temp_g_scores + child.h_scores;
                /*if child node has been evaluated and 
                 the newer f_score is higher, skip*/
                if ((explored.contains(child)) && (temp_f_scores >= child.f_scores)) {
                    continue;
                } /*else if child node is not in queue or 
                 newer f_score is lower*/ 
                else if ((!queue.contains(child)) || (temp_f_scores < child.f_scores)) {
                    child.parent = current;
                    child.g_scores = temp_g_scores;
                    child.f_scores = temp_f_scores;
                    if (queue.contains(child)) {
                        queue.remove(child);
                    }
                    queue.add(child);
                }
            }
        }
    }
}

class Node {

    public final String value;
    public double g_scores;
    public final double h_scores;
    public double f_scores = 0;
    public Edge[] adjacencies;
    public Node parent;

    public Node(String val, double hVal) {
        value = val;
        h_scores = hVal;
    }

    public String toString() {
        return value;
    }
}

class Edge {

    public final double cost;
    public final Node target;

    public Edge(Node targetNode, double costVal) {
        target = targetNode;
        cost = costVal;
    }
}
