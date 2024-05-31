package br.upe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Report {

    private static final String recursosGrandesPath = "./Analises/recursosGrandes.txt";
    private static final String naoRespondidosPath = "./Analises/naoRespondidos.txt";
    private static final String sistemasOperacionaisPath = "./Analises/sistemasOperacionais.txt";

    public void writeFeatures(ArrayList<LogData> logDataList) {
        try {
            File f = new File(Report.recursosGrandesPath);
            if(!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(Report.recursosGrandesPath));
            // header
            writer.write("HTTP, Length, IP\n");
            for(int i = 0; i < logDataList.size(); i++) {
                int status = Integer.parseInt(logDataList.get(i).getStatus());
                int length = Integer.parseInt(logDataList.get(i).getLength());
                if((status >= 200 && status < 300) && length > 2000) {
                    
                    String text = status + ", " + length + ", " + logDataList.get(i).getIP_Adress() + "\n";
                    writer.write(text);
                    
                }
            }
            // close file writer
            writer.close();
            System.out.println("Writing completed!");
            System.out.println();
        }catch (IOException writerEx) {
            System.out.println("Error occurred while writing:");
            writerEx.printStackTrace();
        }
    }

    public void writeUnanswered(ArrayList<LogData> logDataList) {
        try {
            File f = new File(Report.naoRespondidosPath);
            if(!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(Report.naoRespondidosPath));
            // header
            writer.write("HTTP, Solicitation, Date\n");
            for(int i = 0; i < logDataList.size(); i++) {
                int status = Integer.parseInt(logDataList.get(i).getStatus());
                String solicitation = logDataList.get(i).getSolicitation();
                String date = logDataList.get(i).getDateHour();
                String month = "";
                int year = 0;
                Pattern patternSolicitation = Pattern.compile("(.*?) /(.*?) (.*?)");
                Matcher matcherSolicitation = patternSolicitation.matcher(solicitation);
                Pattern patternDate = Pattern.compile("(\\d{1,3})\\/(.*?)\\/(\\d{1,4}):(.*?)");
                Matcher matcherDate = patternDate.matcher(date);
                if(matcherSolicitation.matches()) {
                    solicitation = matcherSolicitation.group(2);
                }
                if(matcherDate.matches()) {
                     month = matcherDate.group(2);
                     year = Integer.parseInt(matcherDate.group(3));
                }

                if((status >= 400 && status < 500) && year == 2021 && month.equals("Nov")) {
                    
                    String text = status + ", /" + solicitation + ", " + month + "/" + year + "\n";
                    writer.write(text);
                    
                }
            }
            // close file writer
            writer.close();
            System.out.println("Writing completed!");
            System.out.println();
        }catch (IOException writerEx) {
            System.out.println("Error occurred while writing:");
            writerEx.printStackTrace();
        }
    }

    public void writeOs(ArrayList<LogData> logDataList) {
        try {
            File f = new File(Report.sistemasOperacionaisPath);
            if(!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(Report.sistemasOperacionaisPath));
            float windows = 0.0f;
            float macintosh = 0.0f;
            float ubuntu = 0.0f;
            float fedora = 0.0f;
            float mobile = 0.0f;
            float others = 0.0f;
            // header
            writer.write("OS, Percentage\n");
            for(int i = 0; i < logDataList.size(); i++) {
                String os = logDataList.get(i).getOperationalSystem();
                String date = logDataList.get(i).getDateHour();
                Pattern patternDate = Pattern.compile("(\\d{1,3})\\/(.*?)\\/(\\d{1,4}):(.*?)");
                Matcher matcherDate = patternDate.matcher(date);
                int year = 0;
                if(matcherDate.matches()) {
                    year = Integer.parseInt(matcherDate.group(3));
                }
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
            writer.write("Windows, " + ((windows/logDataList.size())*100 + "\n"));
            writer.write("Macintosh, " + ((macintosh/logDataList.size())*100 + "\n"));
            writer.write("Ubuntu, " + ((ubuntu/logDataList.size())*100 + "\n"));
            writer.write("Fedora, " + ((fedora/logDataList.size())*100 + "\n"));
            writer.write("Mobile, " + ((mobile/logDataList.size())*100 + "\n"));
            writer.write("Others, " + ((others/logDataList.size())*100 + "\n"));
            // close file writer
            writer.close();
            System.out.println("Writing completed!");
            System.out.println();
        }catch (IOException writerEx) {
            System.out.println("Error occurred while writing:");
            writerEx.printStackTrace();
        }
    }
}
