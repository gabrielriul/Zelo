package br.com.zelo.util;

import br.com.zelo.model.Lembrete;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VerificadorFrequencia {

    public static boolean isParaHoje(Lembrete lembrete) {
        String freq = lembrete.getFrequencia();
        DayOfWeek hoje = LocalDate.now().getDayOfWeek();
        
        if (freq == null || freq.isEmpty()) {
            return false;
        }

        // Se for diário ou baseado em horas, é sempre "hoje"
        if (freq.equals("Diariamente") || freq.contains("A cada")) {
            return true;
        }
        
        return switch (hoje) {
            case MONDAY -> freq.equals("Seg/Qua/Sex");
            case TUESDAY -> freq.equals("Ter/Qui");
            case WEDNESDAY -> freq.equals("Seg/Qua/Sex");
            case THURSDAY -> freq.equals("Ter/Qui");
            case FRIDAY -> freq.equals("Seg/Qua/Sex");
            case SATURDAY -> freq.equals("Sábados") || freq.equals("Fins de Semana (Sab/Dom)");
            case SUNDAY -> freq.equals("Domingos") || freq.equals("Fins de Semana (Sab/Dom)");
            default -> false;
        };
    }
}