package br.upe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Report {

    private static final String recursosGrandesPath = "./projeto-log/src/analises/recursosGrandes.txt";
    private static final String naoRespondidosPath = "./projeto-log/src/analises/naoRespondidos.txt";
    private static final String sistemasOperacionaisPath = "./projeto-log/src/analises/sistemasOperacionais.txt";
    
    private void makeFile(String path, ArrayList<String> lines) {
        try {
            File f = new File(path);
            if(!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for(String line : lines) {
                writer.write(line);
            }
            writer.close();
            System.out.println("Writing completed!");
            System.out.println();
        } catch (IOException writerEx) {
            System.out.println("Error occurred while writing:");
            writerEx.printStackTrace();
        }
    }

    public void writeFeatures(ArrayList<LogData> logDataList) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("HTTP, Length, IP\n");
        for(int i = 0; i < logDataList.size(); i++) {
            int status = logDataList.get(i).getStatus();
            int length = logDataList.get(i).getLength();
            if((status >= 200 && status < 300) && length > 2000) {
                String text = status + ", " + length + ", " + logDataList.get(i).getIP_Adress() + "\n";
                lines.add(text);
            }
        }
        makeFile(Report.recursosGrandesPath, lines);
    }

    public void writeUnanswered(ArrayList<LogData> logDataList) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("HTTP, Solicitation, Date\n");
        for(int i = 0; i < logDataList.size(); i++) {
            int status = logDataList.get(i).getStatus();
            String solicitation = logDataList.get(i).getSolicitation();
            String month = logDataList.get(i).getMonth();
            int year = logDataList.get(i).getYear();

            if((status >= 400 && status < 500) && year == 2021 && month.equals("Nov")) {
                
                String text = status + ", /" + solicitation + ", " + month + "/" + year + "\n";
                lines.add(text);
                
            }
        }
        makeFile(Report.naoRespondidosPath, lines);
    }

    public void writeOs(ArrayList<LogData> logDataList) {
        ArrayList<String> lines = new ArrayList<>();
        float windows = 0.0f;
        float macintosh = 0.0f;
        float ubuntu = 0.0f;
        float fedora = 0.0f;
        float mobile = 0.0f;
        float others = 0.0f;
        lines.add("OS, Percentage\n");
        for(int i = 0; i < logDataList.size(); i++) {
            String os = logDataList.get(i).getOperationalSystem();
            int year = logDataList.get(i).getYear();
            if(year == 2021) {
                if (os.contains("Windows")) {
                    windows++;
                } else if (os.contains("Macintosh")) {
                    macintosh++;
                } else if (os.contains("Ubuntu")) {
                    ubuntu++;
                } else if (os.contains("Fedora")) {
                    fedora++;
                } else if (os.contains("Android") || os.contains("Mobile")) {
                    mobile++;
                } else {
                    others++;
                }
            }
        }
        lines.add("Windows, " + ((windows/logDataList.size())*100 + "\n"));
        lines.add("Macintosh, " + ((macintosh/logDataList.size())*100 + "\n"));
        lines.add("Ubuntu, " + ((ubuntu/logDataList.size())*100 + "\n"));
        lines.add("Fedora, " + ((fedora/logDataList.size())*100 + "\n"));
        lines.add("Mobile, " + ((mobile/logDataList.size())*100 + "\n"));
        lines.add("Others, " + ((others/logDataList.size())*100 + "\n"));
        makeFile(Report.sistemasOperacionaisPath, lines);
    }
}
