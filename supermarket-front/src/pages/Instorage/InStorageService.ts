
import { request } from "umi"
import { ApiResponse, PagerModel } from "../user/model"
import { InStorageRecord } from "./InStorageModel"

export async function inStorageList(params: any) {
    return request<ApiResponse<PagerModel<InStorageRecord[]>>>('/api/in-storage/list', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: params
    })

}

export async function addInStorage(params: any) {
    return request<ApiResponse<InStorageRecord>>('/api/in-storage/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: params
    })

}


export async function getInStorage(id: number) {
    return request<ApiResponse<InStorageRecord>>(`/api/in-storage/get/${id}`, {
        method: 'GET',
    })

}
