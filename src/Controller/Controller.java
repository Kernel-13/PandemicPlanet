/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Network;
import Tools.Graph;

/**
 *
 * @author Ederson
 */
public class Controller {

    private String epidemicModel;
    private String nodesFile;
    private String edgesFile;
    private int infectionRate;
    private int recoveryRate;
    private int numberOfDays;
    private boolean randomWalks;
    private int rwFrequency;
    private boolean quarantine;
    private int quarantineSchedule;
    private String firstInfected;

    public Controller() {
        epidemicModel = "SI";
        nodesFile = "";
        edgesFile = "";
        infectionRate = 50;
        recoveryRate = 50;
        numberOfDays = 100;
        randomWalks = true;
        rwFrequency = 5;
        quarantine = true;
        quarantineSchedule = 5;
        firstInfected = "HUB";
    }

    /**
     * @param epidemicModel the epidemicModel to set
     */
    public void setEpidemicModel(String epidemicModel) {
        this.epidemicModel = epidemicModel;
    }

    /**
     * @param nodesFile the nodesFile to set
     * @return
     */
    public String setNodesFile(String nodesFile) {
        String log = "";
        if (nodesFile.equals("")) {
            log += "* You have to choose the Nodes File\n";
        } else {
            this.nodesFile = nodesFile;
        }
        return log;
    }

    /**
     * @param edgesFile the edgesFile to set
     * @return
     */
    public String setEdgesFile(String edgesFile) {
        String log = "";
        if (edgesFile.equals("")) {
            log += "* You have to choose the Edges File\n";
        } else {
            this.edgesFile = edgesFile;
        }
        return log;
    }

    /**
     * @param infectionRate the infectionRate to set
     * @return
     */
    public String setInfectionRate(String infectionRate) {
        String log = "";

        if (infectionRate.equals("")) {
            log += "* You need to enter the Infection Rate\n";
        } else {
            try {
                int aux = Integer.parseInt(infectionRate);
                if (aux < 1 || aux > 100) {
                    log += "* The Infection Rate Input must be an integer between 1 and\n 100.\n";
                }
                this.infectionRate = aux;
            } catch (NumberFormatException e) {
                log += "* The Infection Rate Input must be an integer between 1 and\n 100.\n";
            }
        }
        return log;
    }

    /**
     * @param recoveryRate the recoveryRate to set
     * @return
     */
    public String setRecoveryRate(String recoveryRate) {
        String log = "";

        if (recoveryRate.equals("")) {
            log += "* You need to enter the Recovery Rate\n";
        } else {
            try {
                int aux = Integer.parseInt(recoveryRate);
                if (aux < 1 || aux > 100) {
                    log += "* The Recovery Rate Input must be an integer between 1 and\n 100.\n";
                }
                this.recoveryRate = aux;
            } catch (NumberFormatException e) {
                log += "* The Recovery Rate Input must be an integer between 1 and\n 100.\n";
            }
        }
        return log;
    }

    /**
     * @param numberOfDays the numberOfDays to set
     * @return
     */
    public String setNumberOfDays(String numberOfDays) {
        String log = "";

        if (numberOfDays.equals("")) {
            log += "* You need to enter the Number of Days\n";
        } else {
            try {
                int aux = Integer.parseInt(numberOfDays);
                if (aux < 2 || aux > 999) {
                    log += "* The Number of Days must be an integer between 2 and 999.\n";
                }
                this.numberOfDays = aux;
            } catch (NumberFormatException e) {
                log += "* The Number of Days must be an integer between 2 and 999.\n";
            }
        }
        return log;
    }

    /**
     * @param randomWalks the randomWalks to set
     */
    public void setRandomWalks(boolean randomWalks) {
        this.randomWalks = randomWalks;
    }

    /**
     * @param rwFrequency the rwFrequency to set
     * @return
     */
    public String setRwFrequency(String rwFrequency) {
        String log = "";

        if (rwFrequency.equals("")) {
            log += "* You need to enter the Number of Days\n";
        } else {
            try {
                int aux = Integer.parseInt(rwFrequency);
                if (aux < 1 || aux >= this.numberOfDays) {
                    log += "* The Frequency of the Random Walks must be an integer\n between 1 and the Number of Days.\n";
                }
                this.rwFrequency = aux;
            } catch (NumberFormatException e) {
                log += "* The Frequency of the Random Walks must be an integer\n between 1 and the Number of Days.\n";
            }
        }
        return log;
    }

    /**
     * @param quarantine the quarantine to set
     */
    public void setQuarantine(boolean quarantine) {
        this.quarantine = quarantine;
    }

    /**
     * @param quarantineSchedule the quarantineSchedule to set
     * @return
     */
    public String setQuarantineSchedule(String quarantineSchedule) {
        String log = "";

        if (quarantineSchedule.equals("")) {
            log += "* You need to enter the Number of Days\n";
        } else {
            try {
                int aux = Integer.parseInt(quarantineSchedule);
                if (aux < 1 || aux >= this.numberOfDays) {
                    log += "* The Day to apply quarantine must be an integer between 1 and\n the Number of Days.\n";
                }
                this.quarantineSchedule = aux;
            } catch (NumberFormatException e) {
                log += "* The Day to apply quarantine must be an integer between 1 and\n the Number of Days.\n";
            }
        }
        return log;
    }

    /**
     * @param choice
     */
    public void setFirstInfected(int choice) {
        switch (choice) {
            case 0:
                this.firstInfected = "HUB";
                break;
            case 1:
                this.firstInfected = "ANTI-HUB";
                break;
            case 2:
                this.firstInfected = "RANDOM";
                break;
        }
    }

    public Graph beginSimulation() {
        Network net = new Network();
        return net.startPandemic(epidemicModel, nodesFile, edgesFile, infectionRate,
                recoveryRate, numberOfDays, randomWalks, rwFrequency,
                quarantine, quarantineSchedule, firstInfected);
    }
}
