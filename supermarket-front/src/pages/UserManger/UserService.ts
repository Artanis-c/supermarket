import { request } from "umi";
import { ApiResponse, PagerModel } from "../user/model";
import { UserModel } from "./model";

export async function userList(params: any) {

    console.log(params)
    return request<ApiResponse<PagerModel<UserModel[]>>>('/api/user/list', {
        method: 'POST',
        data: params,
        headers: {
            'Content-Type': 'application/json',
        },
    })
}


export async function addUser(params: any) {
    return request<ApiResponse<UserModel>>('/api/user/add', {
        method: 'POST',
        data: params,
        headers: {
            'Content-Type': 'application/json',
        },
    })
}

export async function editUser(params: any) {
    return request<ApiResponse<UserModel>>('/api/user/edit', {
        method: 'POST',
        data: params,
        headers: {
            'Content-Type': 'application/json',
        },
    })
}

export async function getUser(id: number) {
    return request<ApiResponse<UserModel>>(`api/user/get/${id}`, {
        method: 'GET',
    })
}