import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한_최단_경로_1504 {

    private static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    private static int[] dist;
    private static ArrayList<Node>[] adjList;
    private final static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        adjList = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int toV1 = dijkstra(1, v1);
        int toV2 = dijkstra(1, v2);
        int neccessaryEdge = dijkstra(v1, v2);
        int v1ToEnd = dijkstra(v1, N);
        int v2ToEnd = dijkstra(v2, N);


        if (toV1 == INF || toV2 == INF || v1ToEnd == INF || v2ToEnd == INF) {
            System.out.println(-1);
            return;
        }

        System.out.println(Math.min(toV1 + v2ToEnd, toV2 + v1ToEnd) + neccessaryEdge);

    }

    private static int dijkstra(int start, int end) {

        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) ->
            Integer.compare(o1.cost, o2.cost)
        );

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.end] < now.cost) {
                continue;
            }

            for (Node next : adjList[now.end]) {
                if (dist[next.end] > now.cost + next.cost) {
                    dist[next.end] = now.cost + next.cost;
                    pq.add(new Node(next.end, dist[next.end]));
                }
            }
        }

        return dist[end];
    }
}
