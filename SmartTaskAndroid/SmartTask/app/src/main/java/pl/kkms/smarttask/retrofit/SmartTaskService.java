package pl.kkms.smarttask.retrofit;

import pl.kkms.smarttask.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Damian on 2017-04-20.
 */
public interface SmartTaskService {
    //  USERS
    @GET(Endpoint.USERS + "/{usercode}")
    Call<User> getUserByUserCode(@Path("usercode") String userCode);

    @PUT(Endpoint.USERS + "/{idtask}/{status}")
    Call<Void> changeTaskStatus(@Path("idtask") int idTask, @Path("status") String status);
}
