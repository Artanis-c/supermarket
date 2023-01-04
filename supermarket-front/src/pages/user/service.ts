import { request } from "umi";
import { UserModel } from "./model";

export async function loginByName(username: string | undefined, password: string | undefined) {
    return request<boolean>('/api/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        params: {
            userName: username,
            password: password
        }
    });
}