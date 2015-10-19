package com.umbrella.cxf.rest.service;

import com.umbrella.ws.model.Student;
import com.umbrella.ws.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by 大洲 on 14-11-23.
 */
@Path("helloEarthService")
@Produces("*/*")
public interface HelloEarthService {
    /**
     * 调用 url 格式为
     * rs/helloEarth/helloEarth/user/yukimura
     * @param userName
     * @return
     */
    @GET
    @Path("/user/{username}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    User getUserByUserName(@PathParam("username")String userName);

    /**
     * rs/helloEarth/helloEarth/student
     * POST 参数类型只能用 @FormParam
     * 响应 JSON
     * @param id
     * @return
     */
    @POST
    @Path("/student")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Student getStudentById(@FormParam("id")int id);

    /**
     * rs/helloEarth/helloEarthService/student?stuName=xxx
     * GET 用 @QueryParam 或 @FormParam 都可以
     * 响应 JSON
     * @param stuName
     * @return
     */
    @GET
    @Path("/getStudentByName")
    @Produces({ MediaType.APPLICATION_JSON })
    Student getStudentByName(@FormParam("stuName")String stuName);

    /**
     * 查询参数
     * url?key=value
     * @param stuName
     * @return
     */
    @GET
    @Path("/search")
    @Produces({ MediaType.APPLICATION_XML })
    Student getStudentByStuName(@QueryParam("stuName")String stuName);
}
