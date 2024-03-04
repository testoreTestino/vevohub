package com.vevohub.integrator.api.trello;

import com.vevohub.integrator.database.DatabaseInit;
import com.vevohub.integrator.database.dao.CandidatesRepository;
import com.vevohub.integrator.database.entity.CandidatesEntity;
import com.vevohub.integrator.service.CandidatesService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TrelloIntegration {

    private static final Set<String> knownCardIds = new HashSet<>();

    @Value("${trello.api.key}")
    private String apiKey;

    @Value("${trello.api.token}")
    private String token;

    @Value("${trello.list.id}")
    private String listId;

    @Autowired
    private DatabaseInit trelloDatabaseIntegration;

    @Autowired
    private CandidatesService candidatesService;

    @Autowired
    private CandidatesRepository candidatesRepository;

    @Scheduled(fixedRate = 60000)
    public void checkForNewCards() {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/lists/" + listId + "/cards")
                    .header("Accept", "application/json")
                    .queryString("key", apiKey)
                    .queryString("token", token)
                    .asJson();

            JSONArray cards = response.getBody().getArray();
            System.out.println(cards);
            for (int i = 0; i < cards.length(); i++) {
                JSONObject card = cards.getJSONObject(i);
                String cardId = card.getString("id");
                if (!knownCardIds.contains(cardId)) {
                    System.out.println("New card added: " + card.getString("name"));
                    knownCardIds.add(cardId);
                    System.out.println(knownCardIds);
                    CandidatesEntity newCandidate = candidatesService.transformCardToCandidateEntity(card);
                    candidatesRepository.save(newCandidate);
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public CandidatesEntity transformCardToCandidateEntity(JSONObject card) {
        CandidatesEntity candidate = new CandidatesEntity();
        // Parse the card description or other fields to set entity properties

        candidate.setFullNameCandidate(card.getString("name"));
        // Set other fields based on your card structure and entity fields
        // Example: candidate.setLocationCity(parseLocationFromDescription(card.getString("desc")));
        // Remember to implement parsing logic based on how your data is structured in Trello cards

        return candidate;
    }
}
