import React, { useState } from 'react'
import Input from '../components/Input'
import Button from '../components/Button'
import { useNavigate } from 'react-router-dom'

const Signup = () => {
  const navigate = useNavigate()
  // State to manage form inputs and errors
  const [formData, setFormData] = useState({
    fullname: '',
    email: '',
    password: '',
  })
  const [errors, setErrors] = useState({
    fullname: '',
    email: '',
    password: '',
  })

  // Live validation logic
  const validateField = (name, value) => {
    switch (name) {
      case 'fullname':
        if (!value) return 'Your full name is required.'
        return ''
      case 'email':
        if (!value) return 'An email is required.'
        if (!/^\S+@\S+\.\S+$/.test(value))
          return 'Please enter a valid email address'
        return ''
      case 'password':
        if (!value) return 'A password is required.'
        if (value.length < 6) return 'Password must be at least 6 characters.'
        return ''
      default:
        return ''
    }
  }

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prev) => ({ ...prev, [name]: value }))

    // Validate this specific field
    const error = validateField(name, value)
    setErrors((prev) => ({ ...prev, [name]: error }))
  }

  const handleSubmit = (e) => {
    e.preventDefault()

    // Final validation before submission
    const newErrors = {
      fullname: validateField('fullname', formData.fullname),
      email: validateField('email', formData.email),
      password: validateField('password', formData.password),
    }

    setErrors(newErrors)

    const hasErrors = Object.values(newErrors).some((error) => error)
    if (!hasErrors) {
      console.log('Form Submitted', formData)
      navigate('/login')
      // Add your submit logic here (e.g., API call)
    }
  }

  // Logic for create account button color change
  const hasErrors =
    Object.values(errors).some((error) => error) ||
    Object.values(formData).some((val) => val.trim() === '')
  return (
    <form onSubmit={handleSubmit} className="max-w-md mx-auto p-6 bg-white">
      <h2 className="text-2xl font-semibold text-gray-800 text-center mb-1">
        Create an account
      </h2>
      <p className="text-sm text-gray-500 text-center mb-6">
        Let's create your account
      </p>
      <Input
        labelTitle="Full Name"
        type="name"
        name="fullname"
        value={formData.fullname}
        onChange={handleChange}
        error={errors.fullname}
      />
      <Input
        labelTitle="Email"
        type="email"
        name="email"
        value={formData.email}
        onChange={handleChange}
        error={errors.email}
      />
      <Input
        labelTitle="Password"
        type="password"
        name="password"
        value={formData.password}
        onChange={handleChange}
        error={errors.password}
      />
      <p className="text-xs text-gray-500 mb-4">
        By signing up you agree to our{' '}
        <span className="font-medium underline">Terms</span>,{' '}
        <span className="font-medium underline">Privacy Policy</span>, and{' '}
        <span className="font-medium  underline">Cookie Use</span>
      </p>
      <div className="py-5">
        <Button
          buttonLabel="Create an Account"
          buttonColor={!hasErrors ? 'bg-[#1A1A1A]' : 'bg-[#CCCCCC]'}
          textColor="text-[#FFFFFF]"
          type="submit"
        />
      </div>

      <p>Or</p>
      <div className="py-5">
        <Button
          buttonLabel="Signup with Google"
          buttonColor="bg-[#FFFFFF]"
          textColor="text-[#1A1A1A]"
        />
      </div>

      <div>
        {' '}
        <Button
          buttonLabel="Signup with Facebook"
          buttonColor="bg-[#1877F2]"
          textColor="text-[#FFFFFF]"
        />
      </div>
    </form>
  )
}

export default Signup
