package ru.nikitinia.odm.bki.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikitinia.odm.bki.client.BKIIncomeClient;
import ru.nikitinia.odm.bki.model.bki.request.Request;
import ru.nikitinia.odm.bki.model.bki.response.Response;

@Service
@RequiredArgsConstructor
public class BKIIncomeService {

    private final BKIIncomeClient BKIIncomeClient;

    public Response processResponse(Request request) {

        Response response =
                BKIIncomeClient.postMessageToBKI(request);

        return response;
    }

}
