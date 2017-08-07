import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import models.ChangeMachine;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());


        get("/changemachine", (request, response) -> {
            ChangeMachine changemachine = new ChangeMachine();
            Map<String, Object> model = new HashMap<String, Object>();

            String amount = request.queryParams("dollarAmount");
            String change = changemachine.makeChange(Float.parseFloat(amount));

            model.put("change", change);
            return new ModelAndView(model, "changemachine.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
