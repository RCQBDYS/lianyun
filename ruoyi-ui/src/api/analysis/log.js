import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listLog(query) {
  return request({
    url: '/system/log/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getLog(logId) {
  return request({
    url: '/system/log/' + logId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addLog(data) {
  return request({
    url: '/analysis/log',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateLog(data) {
  return request({
    url: '/analysis/log',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delLog(logId) {
  return request({
    url: '/system/log/' + logId,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportLog(query) {
  return request({
    url: '/system/log/export',
    method: 'get',
    params: query
  })
}
