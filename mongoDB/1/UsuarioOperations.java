import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class UsuarioOperations {

    public static void main(String[] args) {
        // Conectar ao MongoDB usando a classe personalizada
        MongoDBConnection connection = new MongoDBConnection();
        MongoDatabase database = connection.getDatabase();

        if (database == null) {
            System.err.println("❌ Falha ao conectar ao banco de dados.");
            return;
        }

        MongoCollection<Document> collection = database.getCollection("usuarios");

        // Limpar coleção para teste
        collection.drop();

        // Inserir 3 registros
        Usuario u1 = new Usuario("Alice", 25);
        Usuario u2 = new Usuario("Bob", 30);
        Usuario u3 = new Usuario("Charlie", 35);

        collection.insertMany(Arrays.asList(u1.toDocument(), u2.toDocument(), u3.toDocument()));
        System.out.println("✅ Registros inseridos:");
        listar(collection);

        // Atualizar idade de Bob para 32
        collection.updateOne(eq("nome", "Bob"), set("idade", 32));
        System.out.println("\n🔄 Idade de Bob atualizada:");
        listar(collection);

        // Remover Charlie
        collection.deleteOne(eq("nome", "Charlie"));
        System.out.println("\n🗑️ Charlie removido:");
        listar(collection);

        // Encerrar conexão
        connection.closeConnection();
    }

    public static void listar(MongoCollection<Document> collection) {
        for (Document doc : collection.find()) {
            Usuario usuario = Usuario.fromDocument(doc);
            System.out.println(usuario);
        }
    }
}
