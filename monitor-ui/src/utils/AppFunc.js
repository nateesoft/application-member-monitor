const apiLocalMember = 'http://localhost:5050/api/member';
const apiLocalRedeem = 'http://localhost:5050/api/redeem';
const apiServiceMember = 'http://localhost:5050/api/member/server';
const apiServiceRedeem = 'http://localhost:5050/api/redeem/server';

export const initLoadData = async () => {
  console.log('call=>initLoadData');
  await fetch(apiServiceMember)
  .then(res => res.json())
  .then(result => {
    if (result.insertId && result.insertId > 0) {
      console.log('action:member insertId:', result.insertId)
    }
  })
  .catch(err => console.log(`Error:${err} ${apiServiceMember}`));

  await fetch(apiServiceRedeem)
  .then(res => res.json())
  .then(result => {
    if (result.insertId && result.insertId > 0) {
      console.log('action:redeem insertId:', result.insertId)
    }
  })
  .catch(err => console.log(`Error:${err} ${apiServiceRedeem}`));
}

export const uploadMember = () => {
  console.log('call=>uploadMember');
  return new Promise(async (resolve, reject) => {
    const resMember = await fetch(apiLocalMember)
    .then(res => res.json())
    .catch(err => console.log(`Error:${err} ${apiLocalMember}`));
    if (resMember) {
      resolve('member sync up success');
    } else {
      reject('member sync up failure')
    }
  })
}

export const uploadRedeem = () => {
  console.log('call=>uploadRedeem');
  return new Promise(async (resolve, reject) => {
    const resRedeem = await fetch(apiLocalRedeem)
    .then(res => res.json())
    .catch(err => console.log(`Error:${err} ${apiLocalRedeem}`));
    if (resRedeem) {
      resolve('redeem sync up success');
    } else {
      reject('redeem sync up failure')
    }
  })
}

export const saveRedeemLocal = async (payload) => {
  console.log('call=>saveRedeemLocal');
  return new Promise(async (resolve, reject) => {
    const response = await fetch(apiLocalRedeem, {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: JSON.stringify(payload),
    }).catch((err) => {
      reject(err)
    })
    if(response){
      resolve("Save redeem to local success")
    }else{
      reject('Cannot save redeem to local')
    }
  })
}

export const saveMemberLocal = async (payload) => {
  console.log('call=>saveMemberLocal');
  return new Promise(async (resolve, reject) => {
    const response = await fetch(apiLocalMember, {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: JSON.stringify(payload),
    }).catch((err) => {
      reject(err)
    })
    if(response){
      resolve("Save member to local success")
    }else{
      reject('Cannot save member to local')
    }
  })
}
