package mimly.emergencyroom.repository.dao;

import mimly.emergencyroom.model.dto.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryHelper {

    public static Drug mapToDrug(ResultSet rs, int rowNum) throws SQLException {
        return new Drug(
                rs.getInt("ID"),
                rs.getString("name"),
                rs.getDouble("cost")
        );
    }

    public static EmergencyTeam mapToEmergencyTeam(ResultSet rs, int rowNum) throws SQLException {
        return new EmergencyTeam(
                rs.getInt("ID"),
                rs.getString("name"),
                rs.getInt("emergencyRoomID")
        );
    }

    public static MedicalIssue mapToMedicalIssue(ResultSet rs, int rowNum) throws SQLException {
        return new MedicalIssue(
                rs.getInt("ID"),
                rs.getString("name")
        );
    }

    public static MedicalProcedure mapToMedicalProcedure(ResultSet rs, int rowNum) throws SQLException {
        return new MedicalProcedure(
                rs.getInt("ID"),
                rs.getString("name"),
                rs.getInt("medicalIssueID"),
                rs.getDouble("cost")
        );
    }

    public static Outcome mapToOutcome(ResultSet rs, int rowNum) throws SQLException {
        return new Outcome(
                rs.getInt("ID"),
                rs.getString("name")
        );
    }

    public static Patient mapToPatient(ResultSet rs, int rowNum) throws SQLException {
        return Patient.builder()
                .place(rs.getInt("place"))
                .ID(rs.getInt("ID"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .sex(rs.getString("sex"))
                .age(rs.getInt("age"))
                .priority(new Priority(
                        rs.getInt("priorityID"),
                        rs.getString("priority")))
                .emergencyTeam(new EmergencyTeam(
                        rs.getInt("emergencyTeamID"),
                        rs.getString("emergencyTeam"),
                        rs.getInt("emergencyRoomID")))
                .emergencyRoomID(rs.getInt("emergencyRoomID"))
                .arrival(rs.getString("arrival"))
                .medicalIssue(new MedicalIssue(
                        rs.getInt("medicalIssueID"),
                        rs.getString("medicalIssue")))
                .waitingTime(rs.getInt("waitingTime"))
                .hadToWait(rs.getInt("hadToWait"))
                .build();
    }

    public static Priority mapToPriority(ResultSet rs, int rowNum) throws SQLException {
        return new Priority(
                rs.getInt("ID"),
                rs.getString("name")
        );
    }

    public static Professional mapToProfessional(ResultSet rs, int rowNum) throws SQLException {
        return new Professional(
                rs.getInt("ID"),
                rs.getString("role"),
                rs.getString("username"),
                rs.getString("password")
        );
    }
}
