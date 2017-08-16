package japan.news.tech.takephoto;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    int TAKE_PHOTO_CODE = 0;
    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        //final String dir = "http://japannews.tech/D9VZ68/";
        File newdir = new File(dir);
        newdir.mkdirs();

        Button btn = (Button) findViewById(R.id.btn_TakePhoto);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                String file = dir+count+".jpg";
                System.out.print("Take Photo" + file);
                File newFile = new File(file);
                try{
                    newFile.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();

                }

                Uri outputFileUri = Uri.fromFile(newFile);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(intent, TAKE_PHOTO_CODE);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TAKE_PHOTO_CODE && requestCode == RESULT_OK){
            Log.d("Take Photo", "Pic saved");
        }
    }
    

}
