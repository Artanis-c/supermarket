
import { request } from "umi"
import { ApiResponse, PagerModel } from "../user/model"
import { OutStorageRecord } from "./OutSotrageModel"

export async function outStorageList(params: any) {
    return request<ApiResponse<PagerModel<OutStorageRecord[]>>>('/api/out-storage/list', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: params
    })

}

export async function addOutStorage(params: any) {
    return request<ApiResponse<OutStorageRecord>>('/api/out-storage/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: params
    })

}


export async function getOutStorage(id: number) {
    return request<ApiResponse<OutStorageRecord>>(`/api/out-storage/get/${id}`, {
        method: 'GET',
    })

}
