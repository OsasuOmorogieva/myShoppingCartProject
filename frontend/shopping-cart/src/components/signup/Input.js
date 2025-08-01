import React from 'react'

const Input = ({ labelTitle }) => {
  return (
    <div className="mb-4">
      <label className="block text-sm font-medium text-gray-700 mb-2 text-left">
        {labelTitle}
      </label>
      {labelTitle === 'Email' ? (
        <input
          type="email"
          name="emailInput"
          id="emailInput"
          className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
      ) : labelTitle === 'Password' ? (
        <input
          type="password"
          name="passwordInput"
          id="passwordInput"
          className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
      ) : labelTitle === 'Username' ? (
        <input
          type="text"
          name="usernameInput"
          id="usernameInput"
          className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
      ) : (
        <input
          type="text"
          name="textInput"
          id="textInput"
          className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
      )}
    </div>
  )
}

export default Input
