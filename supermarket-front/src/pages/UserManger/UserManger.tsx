
import { ActionType, DrawerForm, PageContainer, PagtContainer, ProForm, ProFormInstance, ProFormSelect, ProFormText } from '@ant-design/pro-components';
import ProTable, { ProColumns } from '@ant-design/pro-table';
import { Button } from 'antd';
import { set } from 'lodash';
import React, { useRef, useState } from 'react';
import { FormattedMessage } from 'umi';
import { UserModel } from '../user/model';
import { addUser, getUser, userList } from './UserService';
const UserManger: React.FC = () => {

    const [formVisible, setFromVisible] = useState<boolean>(false);
    const ref = useRef<ActionType>();
    const formRef = useRef<ProFormInstance>();
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
            valueType: 'dateTime',
            hideInSearch: true,
        },
        {
            title: '操作',
            valueType: 'option',
            render: (text, record, _, action) => [
                <a key="editable" onClick={async () => {
                    const res = await getUser(record.id)
                    formRef.current?.setFieldsValue(res.data)
                    setFromVisible(true)
                }}>编辑</a>
            ]
        }]


    return (
        <PageContainer>
            <ProTable<UserModel>
                columns={columns}
                request={async (params, sort, filter) => {
                    const res = await userList(params);
                    console.log(res)
                    return {
                        data: res.data.records,
                        total: res.data.total,
                        success: true
                    }
                }}
                toolbar={{
                    actions: [
                        <Button key="primary" type="primary" onClick={() => { setFromVisible(true) }}>
                            新建用户
                        </Button>,
                    ]
                }}
                pagination={{
                    pageSize: 5
                }}
                actionRef={ref}
            />
            <DrawerForm<UserModel>
                formRef={formRef}
                title='新建用户'
                visible={formVisible}
                width="md"
                onVisibleChange={(x) => { setFromVisible(x) }}
                onFinish={async (data) => {
                    await addUser(data)
                    setFromVisible(false)
                    ref.current?.reload();
                    formRef.current?.resetFields();
                }}

            >
                <ProFormText
                    rules={[
                        {
                            required: true,
                        },
                    ]}
                    width="md"
                    name="name"
                    label="用户名"
                    placeholder="请输入用户名"
                />
                <ProFormText.Password
                    name="password"
                    width="md"
                    label="密码"
                    rules={[
                        {
                            required: true,
                            message: (
                                <FormattedMessage
                                    id="pages.login.password.required"
                                    defaultMessage="请输入密码！"
                                />
                            ),
                        },
                    ]}
                />
                <ProFormSelect width="md" name='status' label='状态' options={[{
                    value: 'true',
                    label: '启用',
                },
                {
                    value: 'false',
                    label: '停用',
                },]} />
            </DrawerForm>

        </PageContainer>
    );
};

export default UserManger;