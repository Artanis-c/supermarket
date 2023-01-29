import { request } from "umi";
import { ApiResponse, KeyValuePair, PagerModel } from "../user/model";
import { GoodsModel, GoodsStatisticModel } from "./GoodsModel";


export async function goodsList(params: any) {
    return request<ApiResponse<PagerModel<GoodsModel[]>>>('/api/goods/list', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: params
    })

}

export async function addGoods(params: any) {
    return request<ApiResponse<GoodsModel>>('/api/goods/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: params
    })

}

export async function editGoods(params: any) {
    return request<ApiResponse<number>>('/api/goods/edit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: params
    })

}


export async function getGoods(id: number) {
    return request<ApiResponse<GoodsModel>>(`/api/goods/get/${id}`, {
        method: 'GET',
    })
}

export async function goodsKeyValue() {
    return request<ApiResponse<KeyValuePair[]>>(`/api/goods/keyValue`, {
        method: 'GET',
    })
}

export async function goodsStatistic() {
    return request<ApiResponse<GoodsStatisticModel[]>>(`/api/goods/statistic`, {
        method: 'GET',
    })

}