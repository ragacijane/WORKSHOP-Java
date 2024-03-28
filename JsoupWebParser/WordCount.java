package JsoupWebParser;

import org.jsoup.nodes.Document;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Map.Entry;

public class WordCount extends JScrollPane {
    String text;
    HashMap<String, Integer> counter;
    JPanel panel;
    ArrayList<Entry<String,Integer>> sorted;
    WordCount(Document doc){
        counter = new HashMap<String,Integer>();
        text = doc.body().text();
        countWords();
        sorted = sortByValues(counter);
        panel = new JPanel(new GridLayout(counter.size(),2));
        for(Entry<String,Integer> entry:sorted){
            JLabel wrd=new JLabel(entry.getKey());
            JLabel val=new JLabel(entry.getValue().toString());
            panel.add(wrd);
            panel.add(val);
        }
        setViewportView(panel);
        setPreferredSize(new Dimension(350,350));
    }

    private ArrayList<Entry<String, Integer>> sortByValues(HashMap<String, Integer> counter) {
        ArrayList<Entry<String,Integer>> list = new ArrayList<Entry<String,Integer>>(counter.entrySet());
        Comparator<Entry<String,Integer>> byMapValues = new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        };
        Collections.sort(list,byMapValues);
        return list;
    }

    private void countWords() {
        StringTokenizer stringTokenizer = new StringTokenizer(text);
        while(stringTokenizer.hasMoreTokens()){
            String word=stringTokenizer.nextToken();
            int count = counter.containsKey(word) ? counter.get(word) : 0;
            counter.put(word,count +1 );

        }
    }

}
