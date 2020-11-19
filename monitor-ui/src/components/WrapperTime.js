import React from 'react'
import styled from 'styled-components';

const BoxContain = styled.span`
  font-weight: bold;
  padding: 10px;
  border: 3px solid #bbb;
  margin: 5px;
  font-size: 28px;
`
const BoxMinutes = styled(BoxContain)`
  color: yellow;
`
const BoxSeconds = styled(BoxContain)`
  color: red;
`
const WrapperTime = (props) => {
  return (
    <React.Fragment>
      <BoxMinutes>{props.minute}</BoxMinutes>:
      <BoxSeconds>{props.second}</BoxSeconds>
    </React.Fragment>
  )
}

export default WrapperTime
