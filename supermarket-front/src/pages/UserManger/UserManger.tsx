
import { PageContainer } from '@ant-design/pro-components';
import ProTable, { ProColumns } from '@ant-design/pro-table';
import React from 'react';
import { UserModel } from '../user/model';
import { userList } from './UserService';
const UserManger: React.FC = () => {
    const columns: ProColumns<UserModel>[] = [
        {
            dataIndex: 'id',
            valueType: 'indexBorder',
            width: 48,
        },
        {
            title: '姓名',
            dataIndex: 'name'
        },
        {
            title: '状态',
            dataIndex: 'status',
            hideInSearch: true,
            valueType: 'select',
            valueEnum: {
                true: { text: '启用', value: true },
                false: { text: '禁用', value: false }

            }
        },
        {
            title: '注册时间',
            dataIndex: 'createTime',
            valueType: 'date',
            hideInSearch: true,
        }
    ]


    return (
        <PageContainer>
            <ProTable<UserModel>
                columns={columns}
                request={async (params, sort, filter) => {
                    return userList(params.name)
                }}
            />
        </PageContainer>
    );
};

export default UserManger;