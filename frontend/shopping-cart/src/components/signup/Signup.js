import Input from './Input'
import React from 'react'

const Signup = () => {
  return (
    <div className="max-w-sm mx-auto px-4 py-8 bg-white">
      <h2 className="text-2xl font-semibold text-gray-800 text-center mb-1">
        Create an account
      </h2>
      <p className="text-sm text-gray-500 text-center mb-6">
        Let's create your account
      </p>
      <Input labelTitle="Full Name" />
      <Input labelTitle="Email" />
      <Input labelTitle="Password" />
      <p className="text-xs text-gray-500 mb-4">
        By signing up you agree to our{' '}
        <span className="font-medium underline">Terms</span>,{' '}
        <span className="font-medium underline">Privacy Policy</span>, and{' '}
        <span className="font-medium  underline">Cookie Use</span>
      </p>
    </div>
  )
}

export default Signup
