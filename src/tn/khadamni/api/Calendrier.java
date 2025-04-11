package tn.khadamni.api;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import tn.khadamni.entity.Engagment;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tn.khadamni.gui.SessionController;
import tn.khadamni.service.EngagmentService;

public class Calendrier {

    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "credentials"; // Directory to store user credentials.

    /**
     * Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials/
     * folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);

    /**
     * Creates an authorized Credential object.
     *
     * @param httpTransport The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final HttpTransport httpTransport) throws IOException {
     //vider base de donner 
        Engagment eng = new Engagment();
        EngagmentService e = new EngagmentService();
        e.supprimer1(1);
        // Load client secrets.
        GoogleClientSecrets clientSecrets = null;
        Reader reader = new InputStreamReader(Calendrier.class.getResourceAsStream("credentials.json"));
        clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, SCOPES).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME).build();

        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary").setMaxResults(10).setTimeMin(now).setOrderBy("startTime")
                .setSingleEvents(true).execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                String ch = String.format("%s (%s)\n", event.getSummary(), start);
                data(ch);
            }
        }

    }

    public static void data(String chaine) {
        String eventStr = chaine;
        String name="";
        String eventName = "";
        // Extraire le nom de l'événement
        int idx = eventStr.indexOf("(");
        if (idx > 0) {
            name = eventStr.substring(0, idx).trim();
        }
        Pattern pattern = Pattern.compile("^(.*)\\s+\\(.*\\)$");
        Matcher matcher = pattern.matcher(eventStr);
        if (matcher.matches()) {
            eventName = matcher.group(1);
        } 
       
        // Extraire la date et l'heure
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(eventStr.substring(eventStr.indexOf("(") + 1, eventStr.indexOf(")")), formatter);

        // Obtention du nom du jour de la semaine
        String jourDeLaSemaine = dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRANCE);
        //System.out.println("Jour de la semaine : " + jourDeLaSemaine);

        // Obtention du nom du mois
        String mois = dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.FRANCE);
        // System.out.println("Mois : " + mois);

        // Obtention de l'année, du jour et de l'heure
        int annee = dateTime.getYear();
        int jour = dateTime.getDayOfMonth();
        int heure = dateTime.getHour();

        String date = jour + mois + annee;
        String time = heure + "";
        Engagment eng = new Engagment(name, jourDeLaSemaine, date, time,SessionController.currentuser.getId());
        EngagmentService e = new EngagmentService();
        e.ajouter(eng);
    }

}
