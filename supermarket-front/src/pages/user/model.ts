export type UserModel = {
    id: number,
    name: string,
    password: string,
    status: boolean,
    createTime: Date
}

export type PagerModel<T> = {
    total: number,
    records: T
}

export type ApiResponse<T> = {
    data: T,
    success: boolean,
    errorMessage: string
}

export type KeyValuePair = {
    value: number,
    label: string
}