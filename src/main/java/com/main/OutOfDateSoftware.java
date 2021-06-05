package com.main;
import com.main.model.Software;
import com.main.model.SoftwareStatus;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutOfDateSoftware {
    public static void main(String[] args) {
        List<String> stringList = readFileData("./src/main/java/SoftwareData");
        List<Software> softWaresList = prepareData(stringList);
        Map<String, SoftwareStatus> softwareMap = getLatestSoftwareTypeNameVersionMap(softWaresList);
        printSoftwareMap(softwareMap);
        System.out.println("-----------------------------");
        List<Software> outDatedSoftware = getOutOfDateVersionIsInstalledOnAtLeastTwoDiiferentServers(softWaresList, softwareMap);
        for (Software software : outDatedSoftware) {
            System.out.println(software);
        }
    }

    public static List<String> readFileData(String path) {
        String filePath = path;
        List<String> stringList = new ArrayList<>();
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            String lineText = null;
            while ((lineText = lineReader.readLine()) != null) {
                stringList.add(lineText);
            }
            lineReader.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return stringList;
    }

    private static List<Software> prepareData(List<String> stringList) {
        List<Software> softWares = new ArrayList<>();
        for (String str : stringList) {
            if (!str.isEmpty()) {
                String[] columns = str.split(",");
                System.out.println(columns[3]);
                Software softWare = new Software(columns[0], columns[1], columns[2], columns[3]);
                softWares.add(softWare);
            }
        }
        return softWares;
    }

    private static Map<String, SoftwareStatus> getLatestSoftwareTypeNameVersionMap(Iterable<Software> softWares) {
        Map<String, SoftwareStatus> softwareMap = new HashMap<>();
        for (Software softWare : softWares) {
            String softWareTypename = softWare.getSoftwareTypeName();
            String currentVersion = softWare.getVersionNumber();
            if (softwareMap.containsKey(softWareTypename)) {
                SoftwareStatus softwareStatus = softwareMap.get(softWareTypename);
                int SoftwareCount = softwareStatus.getInstalledCount();
                SoftwareCount++;
                softwareStatus.setInstalledCount(SoftwareCount);
                String latestVersion = softwareStatus.getLatestVersion();
                if (currentVersion.compareTo(latestVersion) > 0) {
                    latestVersion = currentVersion;
                    softwareStatus.setLatestVersion(latestVersion);
                    softwareMap.put(softWareTypename, softwareStatus);
                }
            } else {
                SoftwareStatus softwareStatus = new SoftwareStatus(currentVersion, 1);
                softwareMap.put(softWareTypename, softwareStatus);
            }
        }
        return softwareMap;
    }

    private static List<Software> getOutOfDateVersionIsInstalledOnAtLeastTwoDiiferentServers(Iterable<Software> softWares, Map<String, SoftwareStatus> latestSoftwareVersion) {
        List<Software> softWareList = new ArrayList<>();
        for (Software softWare : softWares) {
            String softwareTypeName = softWare.getSoftwareTypeName();
            String currentVersion = softWare.getVersionNumber();
            SoftwareStatus softwareStatus = latestSoftwareVersion.get(softwareTypeName);
            String softwareStatusLatestVersiont = softwareStatus.getLatestVersion();
            int softwareCount = softwareStatus.getInstalledCount();
            if (softwareCount > 2) {
                if (currentVersion.compareTo(softwareStatusLatestVersiont) < 0) {
                    softWareList.add(softWare);
                }
            }
        }
        return softWareList;
    }

    public static void printSoftwareMap(Map<String, SoftwareStatus> softwareMap) {
        for (Map.Entry<String, SoftwareStatus> entry : softwareMap.entrySet()) {
            String key = entry.getKey();
            SoftwareStatus version = entry.getValue();
            System.out.println("Key: " + key + " " + "Value: " + version);
        }
    }
}

