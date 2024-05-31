package br.upe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class LogData extends Request {

    private ArrayList<LogData> logDataList;

    public ArrayList<LogData> getFileList() {
        return logDataList;
    }
    
    public void setFileList(ArrayList<LogData> logDataList) {
        this.logDataList = logDataList;
    }

    public void read(String fileName) {
        ArrayList<LogData> logDataList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // read the first line
            String line = reader.readLine();

            Pattern pattern = Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) - - \\[(.*?)\\] (.*?) (\\d{3}) (\\d+) (.*?) (.*?) \\((.*?)\\;");
            
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    LogData logdata = new LogData();
                    logdata.setIP_Adress(matcher.group(1));
                    logdata.setDateHour(matcher.group(2));
                    logdata.setSolicitation(matcher.group(3));
                    logdata.setStatus(matcher.group(4));
                    logdata.setLength(matcher.group(5));
                    logdata.setOperationalSystem(matcher.group(8));
                    logDataList.add(logdata);
                }

                line = reader.readLine();
            }
            reader.close();
            
        } catch (IOException readerEx) {
            System.out.println("Error occurred while writing:");
            readerEx.printStackTrace();
        }

        setFileList(logDataList);
    }

    public float makeAverage() {
        float average = 0;
        float divider = 0.0f;
        for(int i = 0; i < this.logDataList.size(); i++) {
            String typeRequest = "";
            int year = 0;
            Pattern patternSolicitation = Pattern.compile("(.*?) /(.*?) (.*?)");
            Matcher matcherSolicitation = patternSolicitation.matcher(this.logDataList.get(i).getSolicitation());
            Pattern patternDate = Pattern.compile("(\\d{1,3})\\/(.*?)\\/(\\d{1,4}):(.*?)");
                Matcher matcherDate = patternDate.matcher(this.logDataList.get(i).getDateHour());
            if(matcherSolicitation.matches()) {
                typeRequest = matcherSolicitation.group(1);
            }
            if(matcherDate.matches()) {
                year = Integer.parseInt(matcherDate.group(3));
            }
            if(typeRequest.equals("\"POST") && year == 2021) {
                average += Integer.parseInt(this.logDataList.get(i).getLength());
                divider += 1.0;
            }
        }
        return (average/divider);
    }

    public void write(String reportType) {
        Report rp = new Report();
        switch (reportType) {
            case "recursosGrandes":
                rp.writeFeatures(this.logDataList);
                break;
            case "naoRespondidos":
                rp.writeUnanswered(this.logDataList);
                break;
            case "OS":
                rp.writeOs(this.logDataList);
                break;
            default:
                System.out.println("Tipo de Relatório Inválido");
                break;
        }
    }
}