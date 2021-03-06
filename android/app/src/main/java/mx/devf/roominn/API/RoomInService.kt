package mx.devf.roominn.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

/**
 * Created by Luis Rios on 08/11/2017.
 */
interface RoomInnService {

    @POST("/v1/auth")
    fun postAuth(@Body request: RequestAuth) : Call<ReponseAuth>

    @POST("/v1/roomie")
    fun postRoomie(@Body request: RequestRoomie) : Call<ResponseRoomie>

    @GET("/v1/roomie")
    fun getRoomie() : Call<List<RoominnRoomie>>

    @GET("/v1/service")
    fun getServices() : Call<List<RoominnService>>

    @GET("/v1/service/byHouse/{house_id}")
    fun getServices(@Path("house_id") house_id: Int) : Call<List<RoominnService>>

    @GET("/v1/service/{service_id}")
    fun getService(@Path("service_id") service_id : Int) : Call<ResponseService>

    @POST("/v1/service")
    fun postService(@Body request: RequestService) : Call<ResponseService>


    data class RequestAuth (val roomie : RoominUser) {}
    data class RequestRoomie (val roomie : RoominnRoomiePost) {}
    data class ResponseRoomie (val roomie : RoominnRoomie) {}

    data class RequestService (val service : RoominnServicePost) {}
    data class ResponseService (val service : RoominnService) {}

    //data class authLogin(val email : String, val password: String) {}
    data class ReponseAuth (val status : Boolean, val token:String) {}

    data class RoominUser(val email: String, val password: String) {}
    data class RoominnHouse (val name: String, val addres: String, val photo: String, val owner: String, val register_date: Date, val update_date: Date){}
    data class RoominnInvite(val email: String, val house_id: Int, val register_date: Date, val update_date: Date) {}
    data class RoominnRoomiePost (val name: String, val email: String, val phone: String, val photo: String, val password: String){}
    data class RoominnRoomie (val id:String, val name: String, val email: String, val phone: String, val photo: String, val password: String, val updatedAt: String, val createdAt: String){}
    data class RoominnServicePost (val name: String, val icon: String, val cost: Double, val payment_due: Int, val house_id: Int?){}
    data class RoominnService (val name: String, val icon: String, val cost: Double, val payment_due: Int, val house_id: Int?, val register_date: String, val update_date: String){}
}