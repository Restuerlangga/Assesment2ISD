import java.util.*;

public class Main {

    static class Graph {
        private Map<String, List<String>> adj;

        public Graph() {
            adj = new HashMap<>();
            
        }

        public void tambahEdge(String dari, String ke) {
            adj.putIfAbsent(dari,new ArrayList<>());
            adj.putIfAbsent(ke, new ArrayList<>());
            adj.get(dari).add(ke);
        }

        public List<String> bfsTraversal(String mulai) {
            List<String> hasil = new ArrayList<>();
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            String lastStep = "eat";
        
            queue.offer(mulai);
            visited.add(mulai);
        
            while (!queue.isEmpty()) {
                String current = queue.poll();
                if (!current.equals(lastStep)) {
                    hasil.add(current);
                }
        
                for (String tetangga : adj.getOrDefault(current, new ArrayList<>())) {
                    if (!visited.contains(tetangga)) {
                        visited.add(tetangga);
                        queue.offer(tetangga);
                    }
                }
            }
        
            if (visited.contains(lastStep)) {
                hasil.add(lastStep);
            }
        
            return hasil;
        }
        

        public List<String> dfsTraversal(String mulai) {
            List<String> hasil = new ArrayList<>();
            Set<String> visited = new HashSet<>();
            dfsHelper(mulai, visited, hasil);
            return hasil;
        }

        private void dfsHelper(String sekarang, Set<String> visited, List<String> hasil) {
            visited.add(sekarang);
            hasil.add(sekarang);

            for (String tetangga : adj.getOrDefault(sekarang, new ArrayList<>())) {
                if (!visited.contains(tetangga)) {
                    dfsHelper(tetangga, visited, hasil);
                }
            }
        }
        
    }

    public static void main(String[] args) {
        Graph langkahBurger = new Graph();

        
        langkahBurger.tambahEdge("preheat oven", "bake bread");
        langkahBurger.tambahEdge("bake bread", "serve bread");
        langkahBurger.tambahEdge("serve bread", "eat");

        langkahBurger.tambahEdge("preheat oven", "preheat pan");
        langkahBurger.tambahEdge("preheat pan", "set plate");
        langkahBurger.tambahEdge("set plate", "serve bread");
        langkahBurger.tambahEdge("serve bread", "eat");
       

        langkahBurger.tambahEdge("preheat oven", "preheat pan");
        langkahBurger.tambahEdge("preheat pan", "set plate");
        langkahBurger.tambahEdge("set plate", "add some pickles");
        langkahBurger.tambahEdge("add some pickles", "eat");

        langkahBurger.tambahEdge("preheat oven", "preheat pan");
        langkahBurger.tambahEdge("preheat pan", "set plate");
        langkahBurger.tambahEdge("set plate", "serve patty");
        langkahBurger.tambahEdge("serve patty", "pour sauce over patty");
        langkahBurger.tambahEdge("pour sauce over patty", "eat");

        langkahBurger.tambahEdge("preheat pan", "add krabby patty");
        langkahBurger.tambahEdge("add krabby patty", "serve patty");
        langkahBurger.tambahEdge("serve patty", "pour sauce over patty");
        langkahBurger.tambahEdge("pour sauce over patty","eat");

        langkahBurger.tambahEdge("preheat pan", "add krabby patty");
        langkahBurger.tambahEdge("add krabby patty", "add tartar sauce");
        langkahBurger.tambahEdge("add tartar sauce", "pour sauce over patty");
        langkahBurger.tambahEdge("pour sauce over patty","eat");





        String titikAwal = "preheat oven";

       
        List<String> urutanBFS = langkahBurger.bfsTraversal(titikAwal);
        System.out.println("a. Urutan langkah memasak burger (berdekatan/BFS):");
        for (String langkah : urutanBFS) {
            System.out.println("- " + langkah);
        }

        
        List<String> urutanDFS = langkahBurger.dfsTraversal(titikAwal);
        System.out.println("\nb. Urutan langkah memasak burger (menjauh/DFS):");
        for (String langkah : urutanDFS) {
            System.out.println("- " + langkah);
        }
    }
   
}