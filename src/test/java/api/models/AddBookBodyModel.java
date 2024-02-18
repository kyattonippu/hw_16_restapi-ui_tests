package api.models;

import lombok.Data;
import java.util.List;

@Data
public class AddBookBodyModel {
    String userId;
    List<ListOfIsbns> collectionOfIsbns;
}
