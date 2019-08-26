import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void startListenersBase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ex1firebaseproject-162ac.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        StorageClient.getInstance().bucket();

        database.child("student").addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildName) {

                String s1 = String.valueOf(dataSnapshot.getValue());
                System.out.println(dataSnapshot.getKey());
                System.out.println(dataSnapshot.getValue());

            }

            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildName) {}
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildName) {}
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("startListeners: unable to attach listener to posts");
                System.out.println("startListeners: " + databaseError.getMessage());
            }
        });

//        database.push().setValue();
    }

    void uploadFileStrogate(String putFile, String nameFoto){
        try {
            FileInputStream serviceAccount1 =
                    new FileInputStream("serviceAccountKey.json");
            FirebaseOptions options1 = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount1))
                    .setDatabaseUrl("https://ex1firebaseproject-162ac.firebaseio.com")
                    .build();
            FirebaseApp fireApp = FirebaseApp.initializeApp(options1);

            StorageClient storageClient = StorageClient.getInstance(fireApp);
            InputStream testFile = new FileInputStream(putFile);
            String blobString = "StudentFoto/" + nameFoto;
            Blob blob = storageClient.bucket("ex1firebaseproject-162ac.appspot.com").create(blobString, testFile);
            blob.getStorage().createAcl(blob.getBlobId(), Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
            System.out.println(blob.getMediaLink());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

//        startListeners();
        Thread.sleep(10000);

    }


}
