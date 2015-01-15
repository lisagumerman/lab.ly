package com.lab.ly.blade;

import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.PrimMinimumSpanningTree;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.VertexNameProvider;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Created by haswell on 1/14/15.
 */
@Ignore
public class SimpleTableScraperTest {


    @Test
    public void ensureLoadingPageDomWorks() {
        final List<Lineup> lineups = getLineups();
        buildGraph(lineups);
    }

    private void buildGraph(List<Lineup> lineups) {
        WeightedGraph<String, DefaultWeightedEdge> graph = new
                ListenableUndirectedWeightedGraph<>(DefaultWeightedEdge.class);
        addVertices(lineups, graph);
        computeEdgeWeights(lineups, graph, getUniquePlayers(lineups));
//        showGraph(graph);

        DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> spanningTree = getSpanningTree(graph);
        showGraph(spanningTree);
    }

    private DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> getSpanningTree(
            WeightedGraph<String, DefaultWeightedEdge> graph) {
        final PrimMinimumSpanningTree<String, DefaultWeightedEdge> tree = new PrimMinimumSpanningTree<>(graph);
        Set<DefaultWeightedEdge> edges = tree.getMinimumSpanningTreeEdgeSet();
        final DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> spanningTree =
                new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        for (DefaultWeightedEdge edge : edges) {
            final String source = graph.getEdgeSource(edge);
            final String target = graph.getEdgeTarget(edge);
            if(!spanningTree.containsVertex(source)) {
                spanningTree.addVertex(source);
            }
            if(!spanningTree.containsVertex(target)) {
                spanningTree.addVertex(target);
            }
            DefaultWeightedEdge e = spanningTree.addEdge(source, target);
            spanningTree.setEdgeWeight(e, graph.getEdgeWeight(edge));
        }
        return spanningTree;
    }

    private void showGraph(final WeightedGraph<String, DefaultWeightedEdge> graph) {
        DOTExporter exporter = getDotExporter(graph);
        try {
            exporter.export(new FileWriter(new File("./teams.dot")), graph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DOTExporter getDotExporter(final WeightedGraph<String, DefaultWeightedEdge> graph) {
        final VertexNameProvider<String> nameProvider = vertex -> vertex.replaceAll("\\s+", "_")
                .replaceAll("\\.", "").replaceAll("-", "_");
        final EdgeNameProvider<DefaultWeightedEdge> edgeEdgeNameProvider = edge -> String.valueOf(graph.getEdgeWeight(edge));
        return new DOTExporter(nameProvider, nameProvider, edgeEdgeNameProvider);
    }

    private Set<String> getUniquePlayers(List<Lineup> lineups) {
        return lineups.stream().flatMap(l -> l.getPlayers().stream()).collect(Collectors.toSet());
    }

    private void computeEdgeWeights(
            List<Lineup> lineups,
            WeightedGraph<String, DefaultWeightedEdge> graph,
            Set<String> uniquePlayers) {
        for(String current : uniquePlayers) {
            for(Lineup lineup : lineups) {
                for(String teammate: lineup.getPlayers()) {
                    if(!current.equals(teammate)) {
                        final DefaultWeightedEdge edge;
                        final double edgeWeight;
                        if(graph.containsEdge(current, teammate)) {
                            edge = graph.getEdge(current, teammate);
                            edgeWeight = graph.getEdgeWeight(edge) + 1;
                        } else if(graph.containsEdge(teammate, current)) {
                            edge = graph.getEdge(teammate, current);
                            edgeWeight = graph.getEdgeWeight(edge) + 1;
                        } else {
                            edge = graph.addEdge(current, teammate);
                            edgeWeight = 0d;
                        }
                        graph.setEdgeWeight(edge, edgeWeight);
                    }
                }

            }
        }
    }

    private void printCsv(List<Lineup> lineups, String s) {
        try {
            final FileWriter writer = new FileWriter(new File("./" + s));
            lineups.forEach(lineup -> {
                try {
                    writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s\n",
                            lineup.pg1, lineup.sg1, lineup.sf1, lineup.pf1, lineup.c1, lineup.g,
                            lineup.f, lineup.util
                            ));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.flush();
            writer.close();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void addVertices(List<Lineup> lineups, WeightedGraph<String, DefaultWeightedEdge> graph) {
        lineups.forEach(lineup -> {
            addPlayer(graph, lineup);
        });
    }

    private void addPlayer(WeightedGraph<String, DefaultWeightedEdge> graph, Lineup lineup) {
        addPlayerByName(graph, lineup.pg1);
        addPlayerByName(graph, lineup.sg1);
        addPlayerByName(graph, lineup.sf1);
        addPlayerByName(graph, lineup.pf1);
        addPlayerByName(graph, lineup.c1);
        addPlayerByName(graph, lineup.g);
        addPlayerByName(graph, lineup.f);
        addPlayerByName(graph, lineup.util);
    }

    private void addPlayerByName(WeightedGraph<String, DefaultWeightedEdge> graph, String name) {
        if(!graph.containsVertex(name)) {
            graph.addVertex(name);
        }
    }

    static List<Lineup> getLineups() {
        Document document = document("blade/tests/nerdnumbers/DraftKings.com.html");
        Elements tables = document.getElementsByTag("table");
        Element playerTable = tables.get(1);
        Elements lineups = playerTable.getElementsByTag("tr");
        List<Lineup> results = lineups.stream().map(lineup -> new Lineup(lineup)).collect(Collectors.toList());
        return results.subList(1, results.size());
    }

    static class Lineup {
        final String
                pg1,
                sg1,
                sf1,
                pf1,c1, g, f, util;

        List<String> getPlayers() {
            return Arrays.asList(pg1, sg1, sf1, pf1, c1, g, f, util);
        }


        Lineup(String pg1, String sg1, String sf1, String pf1, String c1, String g, String f, String util) {
            this.pg1 = pg1;
            this.sg1 = sg1;
            this.sf1 = sf1;
            this.pf1 = pf1;
            this.c1 = c1;
            this.g = g;
            this.f = f;
            this.util = util;
        }

        Lineup(Element row) {
            this(
                    t(row, 0),
                    t(row, 1),
                    t(row, 2),
                    t(row, 3),
                    t(row, 4),
                    t(row, 5),
                    t(row, 6),
                    t(row, 7)
            );
        }


        @Override
        public String toString() {
            return "Lineup{" +
                    "pg1='" + pg1 + '\'' +
                    ", sg1='" + sg1 + '\'' +
                    ", sf1='" + sf1 + '\'' +
                    ", pf1='" + pf1 + '\'' +
                    ", c1='" + c1 + '\'' +
                    ", g='" + g + '\'' +
                    ", f='" + f + '\'' +
                    ", util='" + util + '\'' +
                    '}';
        }
    }

    static String t(Element r, int index) {
        return r.child(index).text().trim().replaceAll("\\s+", " ");
    }


    static final Document document(String resourceName) {
        try {
            return Jsoup.parse(ClassLoader.getSystemResourceAsStream(resourceName), "UTF-8", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
