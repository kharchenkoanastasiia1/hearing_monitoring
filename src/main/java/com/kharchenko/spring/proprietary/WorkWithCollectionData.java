package com.kharchenko.spring.proprietary;

import com.kharchenko.spring.entity.*;

import java.util.ArrayList;
import java.util.List;

public class WorkWithCollectionData {

    public static String toJSONDoctor(List<Doctor> doctorList){
        int index = 0;
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(Doctor doc : doctorList){
            str.append("{").append("\"id\":").append(doc.getId());
            str.append(",\"username\":\"").append(doc.getUsername()).append("\"");
            str.append(",\"name\":\"").append(doc.getName()).append("\"");
            str.append(",\"surname\":\"").append(doc.getSurname()).append("\"");
            str.append(",\"email\":\"").append(doc.getEmail()).append("\"").append("}");

            index++;
            if(index < doctorList.size()){
                str.append(",");
            }
        }
        str.append("]");

        return str.toString();
    }

    public static String toJSONPatient(List<Patient> patientList){
        int index = 0;
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(Patient patient : patientList){
            str.append("{").append("\"id\":").append(patient.getId());
            str.append(",\"username\":\"").append(patient.getUsername()).append("\"");
            str.append(",\"name\":\"").append(patient.getName()).append("\"");
            str.append(",\"surname\":\"").append(patient.getSurname()).append("\"");
            str.append(",\"age\":\"").append(patient.getAge()).append("\"");
            str.append(",\"sex\":\"").append(patient.getSex()).append("\"");
            str.append(",\"email\":\"").append(patient.getEmail()).append("\"").append("}");

            index++;
            if(index < patientList.size()){
                str.append(",");
            }
        }
        str.append("]");

        return str.toString();
    }

    public static String toJSONMessage(List<Message> messageList){
        int index = 0;
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(Message message : messageList){
            str.append("{").append("\"id\":").append(message.getId());
            str.append(",\"username\":\"").append(message.getUsername()).append("\"");
            str.append(",\"text\":\"").append(message.getText()).append("\"");
            str.append(",\"date\":\"").append(message.getDate()).append("\"");
            str.append(",\"status\":\"").append(message.getStatus()).append("\"").append("}");
            index++;
            if(index < messageList.size()){
                str.append(",");
            }
        }
        str.append("]");

        return str.toString();
    }

    public static List<AudiogramForGraphic> getListAudiogramForGraphic(List<Audiogram> audiograms){
        List<AudiogramForGraphic> audiogramForGraphicList = new ArrayList<>();
        for(Audiogram audiogram : audiograms){
            audiogramForGraphicList.add(new AudiogramForGraphic(audiogram));
        }
        return audiogramForGraphicList;
    }

    public static String normAnalize(AudiogramForGraphic audiogram, AudiogramForGraphic normal, int ear, boolean isNorm){
        int betterNorm = 0;
        int norm = 0;
        int significant = 0;
        int insignificant = 0;

        for(int i = 0; i < audiogram.getLeft().length; i++){
            int comparison;
            if(ear == 0){
                comparison = audiogram.getLeft()[i] - normal.getLeft()[i];
            } else{
                comparison = audiogram.getRight()[i] - normal.getRight()[i];
            }
            if(comparison < 0){
                betterNorm++;
            } else if(comparison < 5){
                norm++;
            } else if(comparison < 15){
                insignificant++;
            } else{
                significant++;
            }
        }

        StringBuilder str = new StringBuilder();
        if(isNorm){     //Медиана
            str.append("За персональною нормою: ");
            if(norm == 8 || (norm == 7 && betterNorm == 1)){
                str.append("слух пацієнта у межах норми.");
            } else if(norm == 7 && insignificant == 1){
                str.append("слух пацієнта в цілому нормальний. Незначні коливання можуть бути спричинені помилками вимірювання.");
            } else if(significant > 0){
                str.append("є втрати слуху.");
            } else if(insignificant > 1){
                str.append("спостерігається невелике зниження слуху.");
            } else if(insignificant == 1){
                str.append("слух пацієнта в цілому нормальний. Незначні коливання можуть бути спричинені помилками вимірювання.");
            } else if(betterNorm > 0){
                str.append("слух кращий, ніж у більшості людей у віці пацієнта.");
            }
        } else{         //Популяционные нормы
            str.append("За популяційною нормою: ");
            if(significant > 0){
                str.append("є втрати слуху.");
            } else if(insignificant > 1){
                str.append("спостерігається невелике зниження слуху.");
            } else if(insignificant == 1){
                if(norm < 3){
                    str.append("слух пацієнта в цілому нормальний. Незначні коливання можуть бути спричинені помилками вимірювання.");
                } else{
                    str.append("на одній частоті є невелика втрата слуху. Рекомендуємо пройти тест ще раз.");
                }
            } else if(betterNorm > 0){
                str.append("слух кращий, ніж у більшості людей у віці пацієнта.");
            } else{
                str.append("слух пацієнта у межах норми.");
            }
        }
        return str.toString();
    }
}
