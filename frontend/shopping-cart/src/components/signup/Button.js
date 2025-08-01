import React from 'react'

const Button = ({ buttonLabel, buttonColor, logo }) => {
  return <button className="w-full bg-{buttonColor}">{buttonLabel}</button>
}

export default Button
