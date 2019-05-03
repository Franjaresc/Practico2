package co.edu.icesi.woolf.practico2;
import com.google.gson.Gson;

import java.io.IOException;

public class ServiceManager {

    public static final String USUARIOS_URL = "https://practico2apps.firebaseio.com/Usuarios.json";

    public static class UsuariosGET{

        OnResponseListener listener;
        public UsuariosGET(OnResponseListener listener){
            this.listener=listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                String answer = util.GETrequest(USUARIOS_URL);
                listener.onResponse(answer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public interface OnResponseListener{
            void onResponse(String responce);
        }
    }
    public static class UsuariosPOST{

        OnResponseListener listener;
        public UsuariosPOST(Usuario usuario,OnResponseListener listener){
            this.listener=listener;
            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
            try {
                Gson g = new Gson();
                String answer = util.POSTrequest(USUARIOS_URL,g.toJson(usuario));
                listener.onResponse(answer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public interface OnResponseListener{
            void onResponse(String responce);
        }
    }
}
