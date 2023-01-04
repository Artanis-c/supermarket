import { request } from "umi";
import { UserModel } from "./model";

export async function userList(userName: string) {

    return request<UserModel[]>('/api/user/list', {
        method: 'GET',
        params: {
            userName: userName,
        }
    })
}