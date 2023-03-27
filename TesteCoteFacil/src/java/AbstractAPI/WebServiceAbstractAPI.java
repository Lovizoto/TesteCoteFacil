/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractAPI;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 *
 * @author Usuario
 */
public class WebServiceAbstractAPI {

    private boolean isSuccess = false;

    public ClsPlanet getPlanet(int id) {

        HttpResponse<String> response = Unirest.get("https://swapi.co/api/planets/" + id + "/")
                .header("Content-Type", "application/json")
                .asString().ifFailure((t) -> {
                    System.out.println("t = " + t.getStatusText());
                    System.out.println("t = " + t.getBody());
                }).ifSuccess((t) -> {
                    isSuccess = true;
                    System.out.println(t.getStatusText());
                }).ifFailure((t) -> {
                    System.out.println("t = " + t.getStatusText());
                    System.out.println("t = " + t.getBody());
                    isSuccess = false;
                });

        if (isSuccess) {
            return new Gson().fromJson(response.getBody(), ClsPlanet.class);
        } else {
            return new ClsPlanet();
        }

    }

}
