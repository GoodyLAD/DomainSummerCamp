// Base abstract payload class
abstract class DataPayload {

    // Har payload apna raw content return karega
    public abstract String getRawContent();
}

// JSON payload
class JsonPayload extends DataPayload {

    private String rawContent;

    public JsonPayload(String rawContent) {
        this.rawContent = rawContent;
    }

    @Override
    public String getRawContent() {
        return rawContent;
    }
}

// XML payload
class XmlPayload extends DataPayload {

    private String rawContent;

    public XmlPayload(String rawContent) {
        this.rawContent = rawContent;
    }

    @Override
    public String getRawContent() {
        return rawContent;
    }
}

// Generic processor
// Sirf DataPayload ya uske child classes allow hongi
class PipelineProcessor<T extends DataPayload> {

    public void process(T payload) {

        // Upper bound ki wajah se direct method call possible hai
        System.out.println(
                "Processing Payload: "
                        + payload.getRawContent()
        );
    }
}

// Driver class
public class DataStreamETLPipeline {

    public static void main(String[] args) {

        JsonPayload customerJson =
                new JsonPayload(
                        "{\"customerId\":101,\"name\":\"Aarav\"}"
                );

        XmlPayload orderXml =
                new XmlPayload(
                        "<order><id>5001</id></order>"
                );

        // JSON processor
        PipelineProcessor<JsonPayload> jsonProcessor =
                new PipelineProcessor<>();

        jsonProcessor.process(customerJson);

        // XML processor
        PipelineProcessor<XmlPayload> xmlProcessor =
                new PipelineProcessor<>();

        xmlProcessor.process(orderXml);

        /*
         * Neeche wala code compile hi nahi hoga
         * kyunki String DataPayload ko extend nahi karta
         *
         * PipelineProcessor<String> invalidProcessor =
         *         new PipelineProcessor<>();
         */
    }
}