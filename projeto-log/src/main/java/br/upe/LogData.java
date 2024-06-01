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

            String line = reader.readLine();

            Pattern patternLine = Pattern.compile(
                    "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) - - \\[(.*?)\\] (.*?) (\\d{3}) (\\d+) (.*?) (.*?) \\((.*?)\\)"); // line
            Pattern patternDate = Pattern.compile("(\\d{1,3})\\/(.*?)\\/(\\d{1,4}):(.*?)"); // day/month/year
            Pattern patternSolicitation = Pattern.compile("(.*?) /(.*?) (.*?)"); // solicitation type and link

            while (line != null) {
                Matcher matcherLine = patternLine.matcher(line);
                if (matcherLine.find()) {
                    LogData logData = new LogData();
                    logData.setIP_Adress(matcherLine.group(1));
                    String dateHour = matcherLine.group(2);
                    Matcher matcherDate = patternDate.matcher(dateHour);
                    if (matcherDate.matches()) {
                        logData.setMonth(matcherDate.group(2));
                        logData.setYear(matcherDate.group(3));
                    }
                    String solicitation = matcherLine.group(3);
                    Matcher matcherSolicitation = patternSolicitation.matcher(solicitation);
                    if (matcherSolicitation.matches()) {
                        logData.setTypeRequest(matcherSolicitation.group(1));
                        logData.setSolicitation(matcherSolicitation.group(2));
                    }
                    logData.setStatus(matcherLine.group(4));
                    logData.setLength(matcherLine.group(5));
                    logData.setOperationalSystem(matcherLine.group(8));
                    logDataList.add(logData);
                }

                line = reader.readLine();
            }
            reader.close();

        } catch (IOException readerEx) {
            System.out.println("Error occurred while reading:");
            readerEx.printStackTrace();
        }

        setFileList(logDataList);
    }

    public float makeAverage() {
        float average = 0;
        float divider = 0.0f;
        for (int i = 0; i < this.logDataList.size(); i++) {
            String typeRequest = logDataList.get(i).getTypeRequest();
            // Validation if not snap within method descart
            if (typeRequest != null) {
                int year = logDataList.get(i).getYear();
                if (typeRequest.equals("\"POST") && year == 2021) {
                    average += this.logDataList.get(i).getLength();
                    divider += 1.0;
                }
            }
        }
        return (average / divider);
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