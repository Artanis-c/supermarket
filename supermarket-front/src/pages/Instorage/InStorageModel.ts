export type InStorageModel = {
    id: number,
    goodsId: number,
    number: number,
    userId: number,
    remark: string
}

export type InStorageRecord = {
    id: number,
    goodsId: number,
    number: number,
    userId: number
    goodsName: string,
    userName: string,
    createTime: Date
}