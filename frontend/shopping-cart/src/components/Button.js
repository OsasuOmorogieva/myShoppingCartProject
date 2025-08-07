import React from 'react'

const Button = ({ buttonLabel, buttonColor, textColor, logo = '' }) => {
  return (
    <button
      className={`w-full ${buttonColor} ${textColor} px-4 py-2 border rounded-md `}
    >
      <span> {logo}</span>
      <span>{buttonLabel}</span>
    </button>
  )
}

export default Button
