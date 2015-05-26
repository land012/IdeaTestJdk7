package com.umbrella.cxf.rest.service;

import com.umbrella.ws.model.Student;
import com.umbrella.ws.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by 大洲 on 14-11-23.
 */
@Path("helloEarth")
@Produces("*/*")
public interface HelloEarthService {
    @GET
    @Path("/user/{username}")
    @Produces({MediaType.APPLICATION_XML})
    User getUserByUserName(@PathParam("username")String userName);

    /**
     * 路径参数
     * @param id
     * @return
     */
    @GET
    @Path("/student/{id}")
    Student getStudentById(@PathParam("id")int id);

    /**
     * 查询参数
     * url?key=value
     * @param stuName
     * @return
     */
    @GET
    @Path("/search")
    Student getStudentByStuName(@QueryParam("stuName")String stuName);
}
